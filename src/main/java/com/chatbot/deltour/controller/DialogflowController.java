package com.chatbot.deltour.controller;

import com.chatbot.deltour.model.Message;
import com.chatbot.deltour.sevice.DialogflowService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public void DetectIntent(@RequestBody String queryTxt) throws Exception {

        String sessionId = "abcdefg";
        detectIntentService.detectIntentTexts(queryTxt, sessionId);
    }
}
