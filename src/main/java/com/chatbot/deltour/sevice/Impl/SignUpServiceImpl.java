package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.domain.Account.Account;
import com.chatbot.deltour.dto.SignUpDto;
import com.chatbot.deltour.repository.AccountRepository;
import com.chatbot.deltour.sevice.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ooeunz
 *
 */

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl() {}

    public String signUp(Map<String, Object> json) {

        SignUpDto signUpDto = new SignUpDto(json);

        System.out.println(passwordEncoder.getClass().getName());

        try {
            if ((signUpDto.getEmail() == null && signUpDto.getEmail().length() == 0)
                    | (signUpDto.getEmail() == null && signUpDto.getUsername().length() == 0)
                    | (signUpDto.getPassword() == null && signUpDto.getPassword().length() == 0)) {

                throw new Exception("Email, Username, Password가 채워지지 않았습니다.");
            }

            if (accountRepository.findByEmail(signUpDto.getEmail()) != null) {
                throw new Exception("해당 Email이 이미 존재합니다!");
            }

            Account account = new Account(signUpDto.getEmail(), signUpDto.getUsername(), passwordEncoder.encode(signUpDto.getPassword()));
            accountRepository.save(account);

            return account.getEmail();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
