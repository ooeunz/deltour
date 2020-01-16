package com.chatbot.deltour.domain.detectIntent;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@Document(collection="Intent")
public class Intent {

    @Id
    private String _id;

    private String intent;
    private List<Map<String, Object>> response;

    @Override
    public String toString() {
        return "intent: " + intent;
    }
}


