package com.chatbot.deltour.controller;

import com.chatbot.deltour.model.Message;
import com.chatbot.deltour.sevice.DialogflowService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dialogflow")
public class DialogflowController {
    DialogflowService detectIntentService;

    private DialogflowController(DialogflowService detectIntentService) {
        this.detectIntentService = detectIntentService;
    }

    @PostMapping("/detectintent")
    public Message DetectIntent(String queryTxt) {
        return detectIntentService.detectIntent(queryTxt);
    }
}
