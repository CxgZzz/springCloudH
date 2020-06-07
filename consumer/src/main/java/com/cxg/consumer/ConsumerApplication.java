package com.cxg.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    RestTemplate restTemplateOne(){
        return new RestTemplate();
    }
    @Bean
    @LoadBalanced
    //加上了，此时的就开起了负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
