package com.chatbot.deltour.controller;

import com.chatbot.deltour.dto.request.AuthMemberDTO;
import com.chatbot.deltour.model.member.Member;
import com.chatbot.deltour.sevice.Impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class MemberController {

    MemberServiceImpl memberServiceImpl;

    @Autowired
    private MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    @PostMapping("/signUp")
    public AuthMemberDTO signUp(@RequestBody Member member) {
        return memberServiceImpl.signUp(member);
    }


}