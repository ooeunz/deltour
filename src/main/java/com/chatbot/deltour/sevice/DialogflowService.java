package com.chatbot.deltour.sevice;

import com.chatbot.deltour.dto.response.ResponseContentDTO;
import org.springframework.stereotype.Service;

@Service
public interface DialogflowService {
    ResponseContentDTO detectIntentTexts(String queryTxt, String sessionId) throws Exception;
}
