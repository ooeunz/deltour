package com.chatbot.deltour.model.User;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Document(collection="user")
public class User implements Serializable {

    @Id
    private String idx;

    private String name;

    private String password;

    private String email;

    private String principal;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;

    @Builder
    public User(String name, String password, String email,
                String principal, SocialType socialType,
                LocalDateTime createdDate, LocalDateTime updateDate) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.principal = principal;
        this.socialType = socialType;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }
}
