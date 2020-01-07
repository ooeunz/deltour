package com.chatbot.deltour.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestContentDTO {

    private String content;

    public RequestContentDTO() {
    }

    public RequestContentDTO(String content) {
        this.content = content;
    }
}
