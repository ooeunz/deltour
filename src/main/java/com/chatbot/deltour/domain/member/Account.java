package com.chatbot.deltour.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column
    private String socialId;

    @Column
    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;
}
security