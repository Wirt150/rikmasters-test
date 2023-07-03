package com.example.rikmasters.service.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI dataOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Тестовый проект RikMasters (rikmasters-test)")
                                .version("0.0.1")
                                .description("Система работы с бизнес требованиями. (rikmasters-service)")
                                .contact(
                                        new Contact()
                                                .email("aksenov.viktor.al@gmail.com")
                                                .url("https://github.com/Wirt150")
                                                .name("Виктор Аксенов")
                                )
                );
    }
}