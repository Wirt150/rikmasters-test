package ru.example.rikmasters.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RikGateway {
    public static void main(String[] args) {
        SpringApplication.run(RikGateway.class, args);
    }
}
