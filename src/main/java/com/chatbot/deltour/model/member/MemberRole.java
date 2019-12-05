package com.chatbot.deltour.model.member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "roleId")
public class MemberRole {

    @Id
    private String roleId;
    private String roleEmail;
    private String roleNickNmae;
    private String sessionId;
}
