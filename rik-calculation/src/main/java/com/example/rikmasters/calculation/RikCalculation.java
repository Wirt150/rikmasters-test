package com.example.rikmasters.calculation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaServer
@EnableScheduling
public class RikCalculation {
    public static void main(String[] args) {
        SpringApplication.run(RikCalculation.class, args);
    }
}
