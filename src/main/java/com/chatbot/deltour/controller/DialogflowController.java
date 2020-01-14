package com.chatbot.deltour.controller;

import com.chatbot.deltour.dto.response.ResponseContentDTO;
import com.chatbot.deltour.repository.IntentRepository;
import com.chatbot.deltour.sevice.DialogflowService;
import com.chatbot.deltour.sevice.Impl.DialogflowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dialogflow")
@CrossOrigin("*")
public class DialogflowController {

    DialogflowService dialogflowService;
    IntentRepository intentRepository;

    @Autowired
    private DialogflowController(DialogflowService dialogflowService, IntentRepository intentRepository) {
        this.dialogflowService = dialogflowService;
        this.intentRepository = intentRepository;
    }

    @PostMapping("/detectintent")
    public ResponseContentDTO DetectIntent(@RequestBody String queryTxt) throws Exception {

        String sessionId = "abcdefg";
        return dialogflowService.detectIntentTexts(queryTxt, sessionId);
    }

    @GetMapping("test")
    public String test() {
        String result = "success";
        return result;
    }
}
