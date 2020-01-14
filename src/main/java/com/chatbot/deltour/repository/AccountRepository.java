package com.chatbot.deltour.repository;

import com.chatbot.deltour.domain.member.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);
}