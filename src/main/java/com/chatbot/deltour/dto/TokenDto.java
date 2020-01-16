package com.chatbot.deltour.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class TokenDto {

    @JsonProperty("token")
    private String token;

    public TokenDto(String token) {
        this.token = token;
    }
}
