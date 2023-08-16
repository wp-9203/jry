package com.wp.jry.core;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


public class AssertTest {
    @Test
    public void test1(){
        Object obj = null;
        if(obj == null){ throw new IllegalArgumentException("参数错误");
        }
    }

    @Test
    public void test2(){
        Object obj = null;
        Assert.notNull(obj,"参数错误");
    }
}
