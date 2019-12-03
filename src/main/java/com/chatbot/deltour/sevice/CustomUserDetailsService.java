package com.chatbot.deltour.sevice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface CustomUserDetailsService {
    UserDetails loadUserByUsername(String email);
}
