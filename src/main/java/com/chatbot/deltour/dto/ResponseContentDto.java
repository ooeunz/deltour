package com.chatbot.deltour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseContentDto {
    // message shape
    private String fulfillmentText;
    private String img;
    private String subFulfillmentText;

    // To check isSentByCurrentUser in ReactJs
    private String author;
    public ResponseContentDto() {

    }

    // it doesn't have all requirement parameter. so, it just send only fulfillmentText
    public ResponseContentDto multiTurn(String fulfillmentText) {
        System.out.println("ResponseContentDTO fulfillmentText: " + fulfillmentText);
        return ResponseContentDto.builder()
                .fulfillmentText(fulfillmentText)
                .img("")
                .subFulfillmentText("")
                .author("chatbot")
                .build();
    }

    public ResponseContentDto convertResponseDTO(Map<String, Object> response) {

        ArrayList<String> fulfillmentText = (ArrayList<String>) response.get("fulfillmentText");
        ArrayList<String> subFulfillmentText = (ArrayList<String>) response.get("subFulfillmentText");

        // random index
        int fulfillmentTextIndex = new Random().nextInt(fulfillmentText.size());
        int subFulfillmentTextIndex = new Random().nextInt(subFulfillmentText.size());

        return ResponseContentDto.builder()
                .fulfillmentText(fulfillmentText.get(fulfillmentTextIndex))
                .img((String) response.get("img"))
                .subFulfillmentText(subFulfillmentText.get(subFulfillmentTextIndex))
                .author("chatbot")
                .build();
    }
}

