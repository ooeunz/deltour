package com.chatbot.deltour.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserRole {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }
}
