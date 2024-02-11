package com.swarna.collegeapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "College-API", description = "Backend for College API", version = "v1"))
@SpringBootApplication
public class CollegeApiJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeApiJpaApplication.class, args);
	}

}
