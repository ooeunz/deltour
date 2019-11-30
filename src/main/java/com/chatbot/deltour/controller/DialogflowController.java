package com.chatbot.deltour.controller;

import com.chatbot.deltour.model.detectIntent.Intent;
import com.chatbot.deltour.repository.IntentRepository;
import com.chatbot.deltour.sevice.Impl.DialogflowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Intent DetectIntent(@RequestBody String queryTxt) throws Exception {

        String sessionId = "abcdefg";
        return dialogflowServiceImpl.detectIntentTexts(queryTxt, sessionId);
    }

    // save
    @PostMapping("/save")
    public Intent update(@RequestBody Intent intent){
        this.intentRepository.save(intent);
        System.out.println("sucess?");
        return intent;
    }

    @GetMapping("/findAll")
    public List<Intent> findAll() {
        List<Intent> intents = this.intentRepository.findAll();
        return intents;
    }

    @GetMapping("/find/{intent}")
    public Intent find(@PathVariable("intent") String intent) {
        Intent lst = this.intentRepository.findByIntent(intent);
        return lst;
    }



}
