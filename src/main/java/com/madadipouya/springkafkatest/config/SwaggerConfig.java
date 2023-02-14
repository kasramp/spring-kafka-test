package com.madadipouya.springkafkatest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("Users").pathsToMatch("/v1/**").build();
    }

    @Bean
    public OpenAPI openApiInfo() {
        return new OpenAPI()
                .info(new Info().title("Spring Kafka Test")
                .description("An example about Spring Kafka and testing producer and consumer")
                .version("v0.0.1")
                .license(new License()));
    }
}
