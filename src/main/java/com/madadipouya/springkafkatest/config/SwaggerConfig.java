package com.madadipouya.springkafkatest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/v1/.*"))
                .build()
                .apiInfo(getInfo());
    }

    private ApiInfo getInfo() {
        Contact contact = new Contact("Kasra Madadipouya", "https://geekyhacker.com", "kasra@madadipouya.com");
        return new ApiInfo("Spring Kafka Test",
                "An example about Spring Kafka and testing producer and consumer", "0.1", "",
                contact, "", "", List.of());
    }
}
