package com.EventWise.EventWiseBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

@SpringBootApplication
public class EventWiseBackendApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(EventWiseBackendApplication.class, args);
	}

	@Bean
	public WebJarsResourceResolver webJarsResourceResolver() {
		return new WebJarsResourceResolver();
	}

}
