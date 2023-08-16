package com.wp.srb.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.wp.jry","com.wp.common.api"})
public class ServiceSmsMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsMain.class,args);
    }
}