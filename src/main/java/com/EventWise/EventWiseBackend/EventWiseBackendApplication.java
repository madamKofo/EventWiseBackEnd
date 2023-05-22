package com.EventWise.EventWiseBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

@SpringBootApplication
public class EventWiseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventWiseBackendApplication.class, args);
    }
}
