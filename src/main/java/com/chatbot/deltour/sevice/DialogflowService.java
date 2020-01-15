package com.chatbot.deltour.sevice;

import com.chatbot.deltour.dto.ResponseContentDto;
import org.springframework.stereotype.Service;

@Service
public interface DialogflowService {
    ResponseContentDto detectIntentTexts(String queryTxt, String sessionId) throws Exception;
}
