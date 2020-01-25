package com.chatbot.deltour.sevice;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ooeunz
 */

@Service
public interface SignUpService {

    String signUp(Map<String, Object> json);
}
