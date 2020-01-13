package com.chatbot.deltour.domain.member;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection="member")
public class Member {

    @Id private String id;
    private String email;
    private String username;
    private String password;
    private String authorities = "ROLE_USER";
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

}
