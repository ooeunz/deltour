package com.chatbot.deltour.repository;

import com.chatbot.deltour.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
