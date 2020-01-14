package com.chatbot.deltour.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorization extends UsernamePasswordAuthenticationToken {

    public PreAuthorization(String email, String password) {
        super(email, password);
    }

    public String getEmail() {
        return (String) super.getPrincipal();
    }

    public String getPassword() {
        return (String) super.getCredentials();
    }
}
