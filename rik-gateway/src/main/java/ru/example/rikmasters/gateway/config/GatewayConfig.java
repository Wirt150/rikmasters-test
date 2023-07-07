package ru.example.rikmasters.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayConfig {

    @Bean
    protected RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("rik_calculation",
                        route -> route.path("/rik-calculation/**")
                                .and().method(HttpMethod.GET)
                                .and().method(HttpMethod.POST)
                                .filters(filter -> filter.stripPrefix(1)
                                )
                                .uri("lb://rik-calculation"))
                .route("rik_service",
                        route -> route.path("/rik-service/**")
                                .and().method(HttpMethod.GET)
                                .and().method(HttpMethod.POST)
                                .and().method(HttpMethod.PATCH)
                                .filters(filter -> filter.stripPrefix(1)
                                )
                                .uri("lb://rik-service"))
                .build();
    }
}
