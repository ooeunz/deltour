package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.common.util.Constant;
import com.chatbot.deltour.dto.request.AuthMemberDTO;
import com.chatbot.deltour.dto.request.MyAuthentication;
import com.chatbot.deltour.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthProviderServiceImpl implements AuthenticationProvider {

    @Autowired
    MemberRepository memberRepository;
    MemberServiceImpl memberServiceImpl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        AuthMemberDTO authMemberDTO = memberServiceImpl.findMember(new AuthMemberDTO(email));
        if (null == authMemberDTO || !passwordEncoder.matches(password, authMemberDTO.getPassword())) {
            return null;
        }

        // 로그인한 계정에게 권한 부여
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(Constant.ROLE_TYPE.ROLE_ADMIN.toString()));

        return new MyAuthentication(email, password, grantedAuthorityList, authMemberDTO);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
