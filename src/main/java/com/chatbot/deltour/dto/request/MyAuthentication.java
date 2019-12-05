package com.chatbot.deltour.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


@Getter
@Setter
public class MyAuthentication extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;

    long userId;
    AuthMemberDTO authMemberDTO;

    public MyAuthentication(String email, String password, List<GrantedAuthority> grantedAuthorityList, AuthMemberDTO authMemberDTO) {
        super(email, password, grantedAuthorityList);
        this.authMemberDTO = authMemberDTO;
    }
}
