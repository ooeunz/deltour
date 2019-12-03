package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.dto.MemberDTO;
import com.chatbot.deltour.model.member.Member;
import com.chatbot.deltour.model.member.MemberRole;
import com.chatbot.deltour.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl {

    private MemberRepository memberRepository;

    @Autowired
    private MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDTO convertMemberDTO(Member member) {
        return MemberDTO.builder().email(member.getEmail()).nickName(member.getNickName()).sessionId(member.getSessionId()).build();
    }

    public MemberDTO signUp(Member member) {
        MemberRole memberRole = new MemberRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRole.setRoleNickNmae(member.getNickName());

        memberRepository.save(member);
        return convertMemberDTO(member);
    }
}
