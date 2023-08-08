package com.wp.jry.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.wp.jry"})
public class ServiceCoreMain8081 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreMain8081.class,args);
    }
}
