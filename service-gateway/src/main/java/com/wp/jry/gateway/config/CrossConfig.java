package com.wp.jry.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 网关解决跨域问题
 */
@Configuration
public class CrossConfig {
    @Bean
    public CorsWebFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);//是否允许携带cookie
        List<String> origins = new ArrayList<>();
        origins.add("*");
        config.setAllowedOrigins(origins); //可接收的域,
        List<String> headers = new ArrayList<>();
        headers.add("*");
        config.setAllowedHeaders(headers); //允许携带的头
        List<String> methods = new ArrayList<>();
        methods.add("*");
        config.setAllowedMethods(methods);//允许访问的方式

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return new CorsWebFilter(source);
    }
}
