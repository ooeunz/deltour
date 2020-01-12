package com.chatbot.deltour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DeltourApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(DeltourApplication.class, args);
    }

}
