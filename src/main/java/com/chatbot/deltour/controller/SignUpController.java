package com.chatbot.deltour.controller;

import com.chatbot.deltour.sevice.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public String SignUp(@RequestBody Map<String, Object> obj) {
        return signUpService.signUp(obj);
    }
}
