package com.chatbot.deltour.model.detectIntent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response {
    private Parameter parameter;
    private String fulfillmentText;
    private String img;
}