package com.chatbot.deltour.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDTO {
    private String email;
    private String nickName;
    private String sessionId;
}
