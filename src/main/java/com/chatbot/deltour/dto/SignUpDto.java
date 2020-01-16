package com.chatbot.deltour.dto;


import com.chatbot.deltour.domain.Account.Account;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SignUpDto {

    private String email;
    private String username;
    private String password;

    public SignUpDto(Map<String, Object> json) {
        this.email = (String) json.get("email");
        this.username = (String) json.get("username");
        this.password = (String) json.get("password");
    }
}
