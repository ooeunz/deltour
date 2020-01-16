package com.chatbot.deltour.sevice;

import com.chatbot.deltour.dto.SignUpDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SignUpService {

    String signUp(Map<String, Object> json);
}
