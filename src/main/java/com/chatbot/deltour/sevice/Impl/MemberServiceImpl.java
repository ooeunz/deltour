package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.dto.request.AuthMemberDTO;
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

    public AuthMemberDTO convertAuthMemberDTO(Member member) {
        return AuthMemberDTO.builder().email(member.getEmail()).nickName(member.getNickName()).password(member.getPassword()).build();
    }

    public AuthMemberDTO signUp(Member member) {
        MemberRole memberRole = new MemberRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRole.setRoleNickNmae(member.getNickName());

        memberRepository.save(member);
        return convertAuthMemberDTO(member);
    }

    public AuthMemberDTO findMember(AuthMemberDTO authMemberDTO) {
        Member member = memberRepository.findByEmail(authMemberDTO.getEmail());
        return convertAuthMemberDTO(member);
    }
}
