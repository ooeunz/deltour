package com.chatbot.deltour.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthMemberDTO {
    private String email;
    private String password;
    private String nickName;
    private String sessionId;

    public AuthMemberDTO(String email) {
        this.email = email;
    }
}
