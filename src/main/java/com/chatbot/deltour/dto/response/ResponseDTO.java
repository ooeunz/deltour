package com.chatbot.deltour.dto.response;

import com.chatbot.deltour.model.detectIntent.Parameter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDTO {
    private Parameter parameter;
    private String fulfillmentText;
    private String img;
}
