package com.chatbot.deltour.model.detectIntent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private Parameter parameter;
    private String fulfillmentText;
    private String img;
}
