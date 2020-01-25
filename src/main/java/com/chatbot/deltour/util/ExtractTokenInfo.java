package com.chatbot.deltour.util;

import com.chatbot.deltour.security.tokens.PostAuthorizationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class ExtractTokenInfo {

    public String getUsername(Authentication authentication) {

        PostAuthorizationToken token = (PostAuthorizationToken) authentication;

        return token.getAccountContext().getUsername();
    }
}
