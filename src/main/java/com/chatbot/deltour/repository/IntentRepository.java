package com.chatbot.deltour.repository;

import com.chatbot.deltour.domain.detectIntent.Intent;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentRepository extends MongoRepository<Intent, String> {

    Intent findByIntent(String intent);
}
