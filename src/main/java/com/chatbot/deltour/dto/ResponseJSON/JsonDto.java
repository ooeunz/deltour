package com.chatbot.deltour.dto.ResponseJSON;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class JsonDto {

    private boolean status;
    private String message;
    private Map<String, Object> data;
}
