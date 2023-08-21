package com.wp.jry.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.wp.jry","com.wp.common.api"})
@EnableFeignClients
public class ServiceSmsMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsMain.class,args);
    }
}