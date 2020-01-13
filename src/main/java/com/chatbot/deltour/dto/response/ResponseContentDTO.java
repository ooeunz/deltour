package com.chatbot.deltour.dto.response;

import com.chatbot.deltour.domain.detectIntent.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseContentDTO {
    private String fulfillmentText;
    private String img;
    private String subFulfillmentText;

    public ResponseContentDTO() {

    }

    // it doesn't have all requirement parameter. so, it just send only fulfillmentText
    public ResponseContentDTO multiTurn(String fulfillmentText) {
        System.out.println("ResponseContentDTO fulfillmentText: " + fulfillmentText);
        return ResponseContentDTO.builder()
                .fulfillmentText(fulfillmentText)
                .img("")
                .subFulfillmentText("")
                .build();
    }

    public ResponseContentDTO convertResponseDTO(Map<String, Object> response) {

        ArrayList<String> fulfillmentText = (ArrayList<String>) response.get("fulfillmentText");
        ArrayList<String> subFulfillmentText = (ArrayList<String>) response.get("subFulfillmentText");

        // random index
        int fulfillmentTextIndex = new Random().nextInt(fulfillmentText.size());
        int subFulfillmentTextIndex = new Random().nextInt(subFulfillmentText.size());

        return ResponseContentDTO.builder()
//                .parameter(response.getParameter())
                .fulfillmentText(fulfillmentText.get(fulfillmentTextIndex))
                .img((String) response.get("img"))
                .subFulfillmentText(subFulfillmentText.get(subFulfillmentTextIndex))
                .build();
    }
}

