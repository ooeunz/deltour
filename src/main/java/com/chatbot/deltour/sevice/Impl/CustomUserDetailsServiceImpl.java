package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.repository.MemberRepository;
import com.chatbot.deltour.sevice.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    MemberRepository memberRepository;

    @Autowired
    private CustomUserDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return null;
    }



}
