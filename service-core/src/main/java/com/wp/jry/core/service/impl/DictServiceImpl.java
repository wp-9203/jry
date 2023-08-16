package com.wp.jry.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wp.common.api.exception.BusinessException;
import com.wp.common.api.result.ResponseEnum;
import com.wp.jry.core.listeneer.ExcelDictListener;
import com.wp.jry.core.pojo.dto.ExcelDictDTO;
import com.wp.jry.core.pojo.entity.Dict;
import com.wp.jry.core.dao.DictMapper;
import com.wp.jry.core.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Service
@Slf4j
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Resource
    private DictMapper dictMapper;

    @Resource
    private RedisTemplate redisTemplate;
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void importData(InputStream inputStream) {
        log.info("文件开始导入！");
        EasyExcel.read(inputStream,ExcelDictDTO.class,new ExcelDictListener(dictMapper)).sheet().doRead();
    }

    @Override
    public List<ExcelDictDTO> listDictData() {
        List<Dict> dicts = dictMapper.selectList(null);
        //
        List<ExcelDictDTO> dictDTOS = new ArrayList<>(dicts.size());

        dicts.forEach(dict ->{
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict , excelDictDTO);
            dictDTOS.add(excelDictDTO);
        });
        return dictDTOS;
    }

    @Override
    public List<Dict> listByParentId(Long parentId) {
        //首先查询redis中是否存在数据列表
        try {
            log.info("从redis中获取数据列表-key:=srb:core:dictList:" + parentId);
            List<Dict> redisDicts =
                    (List<Dict>)redisTemplate.opsForValue().get("srb:core:dictList:" + parentId);
            if(redisDicts != null){
                return redisDicts;
            }
        } catch (Exception e) {
            //redis查询发生异常不要直接退出程序，此处记录异常信息。去数据库查询结果
            log.error("redis服务器异常", ExceptionUtils.getStackTrace(e));
        }
        QueryWrapper<Dict> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",parentId);
        log.info("从数据库获取数据列表");
        List<Dict> dicts = dictMapper.selectList(queryWrapper);
        dicts.forEach( dict -> {
            dict.setHasChildren(this.hasChildren(dict.getId()));
        });

        try {
            //将数据存入redis中
            log.info("将数据存入redis中-key:=srb:core:dictList:" + parentId);
            redisTemplate.opsForValue().set("srb:core:dictList:" + parentId,dicts);
        } catch (Exception e) {
            log.error("redis服务器异常", ExceptionUtils.getStackTrace(e));
        }

        return dicts;
    }

    private boolean  hasChildren(Long parentId){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",parentId);

        Integer count = dictMapper.selectCount(queryWrapper);
        return count.intValue() > 0 ? true:false;
    }
    @Resource
    private SqlSessionFactory sqlSessionFactory;
    @Override
    public boolean batchInsert(List<Dict> list, int batchSize) {
        SqlSession batchSqlsession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        DictMapper mapper = batchSqlsession.getMapper(DictMapper.class);
        int i = 1;
        int size = list.size();
        try{
            for (Dict dict:list) {
                mapper.save(dict);
                if( i % batchSize == 0 || i == size ){
                    batchSqlsession.flushStatements();
                }
                i++;
            }
            batchSqlsession.commit();
            return true;
        } catch (Exception e ){
            batchSqlsession.rollback();
            throw new BusinessException(ResponseEnum.iMPORT_DATA_ERROE);
        } finally {
            batchSqlsession.close();
        }

    }
}
