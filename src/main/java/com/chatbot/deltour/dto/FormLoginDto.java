package com.chatbot.deltour.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormLoginDto {

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("password")
    private String password = null;
}
