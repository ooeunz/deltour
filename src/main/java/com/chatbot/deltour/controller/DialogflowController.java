package com.chatbot.deltour.controller;

import com.chatbot.deltour.dto.FulfillmentTextDto;
import com.chatbot.deltour.security.tokens.PostAuthorizationToken;
import com.chatbot.deltour.sevice.DialogflowService;
import com.chatbot.deltour.sevice.RedirectService;
import com.chatbot.deltour.util.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ooeunz
 *
 */

@RestController
@RequestMapping("/api")
public class DialogflowController {

    @Autowired
    private DialogflowService dialogflowService;

    @Autowired
    private RedirectService redirectService;

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/detectIntent")
    @PostAuthorize("hasRole('ROLE_USER')")
    public ResponseMessage DetectIntent(@RequestBody Map<String, Object> req, Authentication authentication) throws Exception {

        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        String sessionId = token.getAccountContext().getUsername();

        return dialogflowService.detectIntentTexts((String) req.get("queryTxt"), sessionId);
    }

    @PostMapping("redirect")
    @PostAuthorize("hasRole('ROLE_USER')")
    public ResponseMessage Redirect(@RequestBody Map<String, Object> req, Authentication authentication) throws JsonProcessingException {

        PostAuthorizationToken token = (PostAuthorizationToken) authentication;


        return redirectService.RedirectQueryTxt((String) req.get("queryTxt"), token.getAccountContext().getUsername());
    }

    @GetMapping("/getUsername")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUsername(Authentication authentication) {

        PostAuthorizationToken token = (PostAuthorizationToken) authentication;

        return token.getAccountContext().getUsername();
    }
}
