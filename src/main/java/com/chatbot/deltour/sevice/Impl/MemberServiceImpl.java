//package com.chatbot.deltour.sevice.Impl;
//
//import com.chatbot.deltour.domain.member.Member;
//import com.chatbot.deltour.repository.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class MemberServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    //loadUserByUserName
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Member member = memberRepository.findByEmail(email);
//        if (member == null) {
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//        return new User(member.getEmail(), member.getPassword(), authorities);
//    }
//
//
//    // findAll
//    public List<Member> findAll() {
//        return memberRepository.findAll();
//    }
//
//    // save
//    public Member save(Member member) {
//        member.setPassword(passwordEncoder.encode(member.getPassword()));
//        return memberRepository.save(member);
//    }
//
//    // findByEmail
//    public Member findByEmail(String email) {
//        return memberRepository.findByEmail(email);
//    }
//
//    // delete
//    public void delete(String id) {
//        memberRepository.deleteById(id);
//    }
//
//
//}
