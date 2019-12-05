package com.chatbot.deltour.sevice;

import com.chatbot.deltour.dto.request.AuthMemberDTO;
import com.chatbot.deltour.model.member.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    AuthMemberDTO signUp(Member member);
    AuthMemberDTO selectMember(AuthMemberDTO authMemberDTO);
}
