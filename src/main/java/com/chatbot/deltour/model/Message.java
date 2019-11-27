package com.chatbot.deltour.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Configuration
public class Message {
    @Id
    private String id;

    private String message;
    private String img;
}
