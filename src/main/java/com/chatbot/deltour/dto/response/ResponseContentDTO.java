package com.chatbot.deltour.dto.response;

import com.chatbot.deltour.model.detectIntent.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
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

    public ResponseContentDTO convertResponseDTO(Response response) {
        // random index
        int fulfillmentTextIndex = new Random().nextInt(response.getFulfillmentText().size());
        int subfulfillmentTextIndex = new Random().nextInt(response.getSubFulfilmentText().size());

        return ResponseContentDTO.builder()
//                .parameter(response.getParameter())
                .fulfillmentText(response.getFulfillmentText().get(fulfillmentTextIndex))
                .img(response.getImg())
                .subFulfillmentText(response.getSubFulfilmentText().get(subfulfillmentTextIndex))
                .build();
    }
}

