package com.chatbot.deltour.controller;

import com.chatbot.deltour.dto.ResponseContentDto;
import com.chatbot.deltour.repository.IntentRepository;
import com.chatbot.deltour.security.tokens.PostAuthorizationToken;
import com.chatbot.deltour.sevice.DialogflowService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class DialogflowController {

    DialogflowService dialogflowService;
    IntentRepository intentRepository;

    public DialogflowController() {

    }

    private DialogflowController(DialogflowService dialogflowService, IntentRepository intentRepository) {
        this.dialogflowService = dialogflowService;
        this.intentRepository = intentRepository;
    }

    @PostMapping("/detectintent")
    @PostAuthorize("hasRole('ROLE_USER')")
    public ResponseContentDto DetectIntent(@RequestBody String queryTxt) throws Exception {

        String sessionId = "abcdefg";

        return dialogflowService.detectIntentTexts(queryTxt, sessionId);
    }

    @GetMapping("/getUsername")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUsername(Authentication authentication) {

        PostAuthorizationToken token = (PostAuthorizationToken) authentication;

        return token.getAccountContext().getUsername();
    }
}
