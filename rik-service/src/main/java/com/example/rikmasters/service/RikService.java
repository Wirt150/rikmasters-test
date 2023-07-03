package com.example.rikmasters.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RikService {
    public static void main(String[] args) {
        SpringApplication.run(RikService.class, args);
    }
}
