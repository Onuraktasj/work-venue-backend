package com.workvenue.backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@SpringBootApplication
public class WorkvenueBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkvenueBackendApplication.class, args);
	}

	//Custom Beans
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	//Belong Swagger Below Two Methods
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Spring Boot Simple REST API",
				"Spring Boot Simple REST API Swagger Documentation",
				"Version 1",
				"urn:tos",
				new Contact("Engineer", "medium.com/@mbahardogan", "mbahardogan@hotmail.com"),
				"Apache 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<>());
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.workvenue.backend"))
				.paths(PathSelectors.any())
				.build();
	}
}
