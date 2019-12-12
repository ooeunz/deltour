package com.chatbot.deltour.controller;

import com.chatbot.deltour.anotation.SocialUser;
import com.chatbot.deltour.model.User.User;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginSucess")
    public String loginComplete(@SocialUser User user) {
        return "redirect:/chat";
    }

}