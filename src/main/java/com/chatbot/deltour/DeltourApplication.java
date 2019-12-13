package com.chatbot.deltour;

import com.chatbot.deltour.model.User.User;
import com.chatbot.deltour.repository.UserRepository;
import com.chatbot.deltour.resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DeltourApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(DeltourApplication.class, args);
    }

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }

}
