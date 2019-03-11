package com.example.microserviceconsumermoviefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Miskai
 */
@EnableFeignClients
@SpringBootApplication
public class MicroserviceConsumerMovieFeignApplication {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerMovieFeignApplication.class, args);
    }

}
