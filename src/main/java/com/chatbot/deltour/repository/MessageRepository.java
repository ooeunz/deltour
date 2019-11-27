package com.chatbot.deltour.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "sad")
public class MessageRepository {
    @Id
    private String id;

    private String message;
    private String img;


}
