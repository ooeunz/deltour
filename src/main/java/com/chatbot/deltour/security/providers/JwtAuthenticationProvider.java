package com.chatbot.deltour.security.providers;

import com.chatbot.deltour.security.AccountContext;
import com.chatbot.deltour.security.jwt.JwtDecoder;
import com.chatbot.deltour.security.tokens.JwtPreProcessingToken;
import com.chatbot.deltour.security.tokens.PostAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = (String) authentication.getPrincipal();
        AccountContext context = jwtDecoder.decodeJwt(token);

        return PostAuthorizationToken.getTokenFromAccountContext(context);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
