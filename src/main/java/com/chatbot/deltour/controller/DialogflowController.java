package com.chatbot.deltour.controller;

import com.chatbot.deltour.dto.response.ResponseDTO;
import com.chatbot.deltour.repository.IntentRepository;
import com.chatbot.deltour.sevice.Impl.DialogflowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dialogflow")
public class DialogflowController {

    DialogflowServiceImpl dialogflowServiceImpl;
    IntentRepository intentRepository;

    @Autowired
    private DialogflowController(DialogflowServiceImpl dialogflowServiceImpl, IntentRepository intentRepository) {
        this.dialogflowServiceImpl = dialogflowServiceImpl;
        this.intentRepository = intentRepository;
    }

    @PostMapping("/detectintent")
    public ResponseDTO DetectIntent(@RequestBody String queryTxt) throws Exception {

        String sessionId = "abcdefg";
        return dialogflowServiceImpl.detectIntentTexts(queryTxt, sessionId);
    }


}
