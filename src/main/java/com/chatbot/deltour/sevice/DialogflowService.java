package com.chatbot.deltour.sevice;

import com.chatbot.deltour.model.detectIntent.Intent;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface DialogflowService {
    Intent detectIntentTexts(String queryTxt, String sessionId) throws Exception;
}
