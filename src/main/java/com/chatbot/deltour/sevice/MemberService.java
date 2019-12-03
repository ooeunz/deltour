package com.chatbot.deltour.sevice;

import com.chatbot.deltour.dto.MemberDTO;
import com.chatbot.deltour.model.member.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    MemberDTO signUp(Member member);
}
