package com.chatbot.deltour.sevice;

import com.chatbot.deltour.dto.response.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface DialogflowService {
    ResponseDTO detectIntentTexts(String queryTxt, String sessionId) throws Exception;
}
