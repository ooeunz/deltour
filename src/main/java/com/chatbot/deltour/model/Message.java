package com.chatbot.deltour.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class Message {
    private String header;
    private String body;
    private String img;
}
