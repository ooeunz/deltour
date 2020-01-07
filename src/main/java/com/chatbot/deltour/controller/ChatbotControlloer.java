package com.chatbot.deltour.controller;

import com.chatbot.deltour.dto.request.RequestContentDTO;
import com.chatbot.deltour.dto.response.ResponseContentDTO;
import com.chatbot.deltour.repository.IntentRepository;
import com.chatbot.deltour.sevice.Impl.DialogflowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatbotControlloer {

    @Autowired
    private SimpMessagingTemplate template;
    private DialogflowServiceImpl dialogflowServiceImpl;
    private IntentRepository intentRepository;

    @MessageMapping("/detectIntent")
    @SendTo("/topic/{chatbotRoom}")
    public void detectIntent(@DestinationVariable String chatbotRoom, RequestContentDTO requestContentDTO) throws Exception {

        String sessionId = "abcdefg";
        ResponseContentDTO responseContentDTO = dialogflowServiceImpl.detectIntentTexts(requestContentDTO.getContent(), sessionId);

        template.convertAndSend("/topic/" + chatbotRoom, responseContentDTO);
        Thread.sleep(1000); // simulated delay

        if (responseContentDTO.getSubFullfillmentText() != null) {
            template.convertAndSend("/topic/" + chatbotRoom, responseContentDTO.getSubFullfillmentText());
        }
    }
}
