package com.wp.jry.sms.client.fallback;

import com.wp.jry.sms.client.CoreUserInfoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CoreUserInfoClientFallback implements CoreUserInfoClient {
    @Override
    public boolean checkMobile(String mobile) {
        log.error("远程调用core手机号是否注册校验失败,服务熔断");
        return false;
    }
}
