package com.workvenue.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage(
                        "com.workvenue.backend")).paths(PathSelectors.any())
                .build();
    }



    private ApiInfo apiInfo() {
        return new ApiInfo("Work Venue APIs", "Work Venue" + " REST API Swagger " + "Documentation", "Version 1",
                "urn:tos", ApiInfo.DEFAULT_CONTACT, "Apache 2.0",
                "https" + "://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
    }
}
