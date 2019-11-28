package com.chatbot.deltour.repository;

import com.chatbot.deltour.model.Message;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Document(collation = "intent")
public interface MessageRepository extends MongoRepository<Message, String> {

    JSONObject findByIntent(@Param("intent") String intent);
}
