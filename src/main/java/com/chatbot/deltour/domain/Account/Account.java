package com.chatbot.deltour.domain.Account;

import lombok.*;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column
    private String socialId;

    @Column
    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getSocialId() {
        return socialId;
    }

    public SocialType getSocialType() {
        return socialType;
    }

    public Account() {

    }

    public Account(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}