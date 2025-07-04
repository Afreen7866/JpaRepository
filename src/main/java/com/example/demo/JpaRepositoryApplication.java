package com.example.demo;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
/*@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot rest api documentation",
				description = "Spring Boot rest api documentation",
				version = "v1.0",
				contact = @Contact(
						name  = "AFreen Sulthana",
						email = "afreensulthana1205@gmail.com",
						url = "https://www.javaguides.net"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.javaguides.net/license"
						
				)
				
		)
)*/
public class JpaRepositoryApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JpaRepositoryApplication.class, args);
	}

}
