package com.chatbot.deltour.controller;

import com.chatbot.deltour.model.member.Member;
import com.chatbot.deltour.sevice.Impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/auth")
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;

    @GetMapping("/member")
    public List<Member> listUser() {
        return memberService.findAll();
    }

    @PostMapping("/member")
    public Member create(@RequestBody Member member) {
        return memberService.save(member);
    }

    @DeleteMapping("member")
    public String delete(@PathVariable String id) {
        memberService.delete(id);
        return "sucess";
    }



}