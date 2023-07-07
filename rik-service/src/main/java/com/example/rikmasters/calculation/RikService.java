package com.example.rikmasters.calculation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RikService {
    public static void main(String[] args) {
        SpringApplication.run(RikService.class, args);
    }
}
