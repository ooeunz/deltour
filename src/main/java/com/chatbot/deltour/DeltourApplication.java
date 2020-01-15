package com.chatbot.deltour;

import com.chatbot.deltour.domain.Account.Account;
import com.chatbot.deltour.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DeltourApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(DeltourApplication.class, args);
    }

//    @Bean
//    CommandLineRunner bootstrapeTestAccount(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            Account account = new Account();
//            accountRepository.save(account.testBuilder("yuns994@gmail.com", "ooeunz", passwordEncoder.encode("123123")));
//        };
//    }
}
