package com.chatbot.deltour.model.member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Document(collection="member")
public class Member {

    @Id
    private String id;

    private String email;
    private String password;
    private String nickName;
    private String sessionId;
}
