package com.chatbot.deltour.sevice;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public interface AuthProviderService {
    Authentication authenticate(Authentication authentication) throws AuthenticationException;
}
