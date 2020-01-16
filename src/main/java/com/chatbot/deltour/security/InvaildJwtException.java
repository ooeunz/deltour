package com.chatbot.deltour.security;

public class InvaildJwtException extends RuntimeException {

    public InvaildJwtException(String msg) {
        super(msg);
    }
}
