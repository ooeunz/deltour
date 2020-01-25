package com.chatbot.deltour.sevice;

import com.chatbot.deltour.dto.FulfillmentTextDto;
import com.chatbot.deltour.util.ResponseMessage;
import org.springframework.stereotype.Service;

/**
 * @author ooeunz
 */

@Service
public interface DialogflowService {
    ResponseMessage detectIntentTexts(String queryTxt, String sessionId) throws Exception;
}
