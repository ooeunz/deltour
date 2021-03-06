package com.chatbot.deltour.domain.detectIntent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Response {
    private Map<String, Object> parameter;
    private List<String> fulfillmentText;
    private String img;
    private List<String> subFulfilmentText;
}
