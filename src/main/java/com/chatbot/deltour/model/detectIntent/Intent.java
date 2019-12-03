package com.chatbot.deltour.model.detectIntent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@ToString
@Document(collection="intent")
public class Intent {

    @Id
    private String id;

    private String intent;
    private List<Response> response;

    @Override
    public String toString() {
        return "intent: " + intent;
    }
}


