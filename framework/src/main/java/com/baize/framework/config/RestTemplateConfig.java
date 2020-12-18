package com.baize.framework.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lubinjia
 * @create 2020/11/22 18:53
 */
@Configuration
public class RestTemplateConfig {

    @Bean
//    @LoadBalanced
    public org.springframework.web.client.RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
