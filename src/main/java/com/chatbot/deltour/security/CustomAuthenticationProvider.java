package com.chatbot.deltour.security;

import com.chatbot.deltour.dto.request.UserDTO;
import com.chatbot.deltour.sevice.Impl.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    private PasswordEncoder encoder;



    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder encoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("CustomUserAuthenticationProvider.authenticate :::: {}",authentication.toString());

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;

        String userId = token.getName();

        UserDTO user = null;

        if(!StringUtils.isEmpty(userId)) {
            user = (UserDTO) customUserDetailsService.loadUserByUsername(userId);
        }

        if(ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("Invalid username");
        }

        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());

        String password = user.getPassword();

        if(!StringUtils.equals(password, encoder.encode(String.valueOf(token.getCredentials())))) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        log.debug("CustomUserAuthenticationProvider.supports ::::");
        return UsernamePasswordAuthenticationToken
                .class.equals(authentication);
    }

}



