package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.dto.request.UserDTO;
import com.chatbot.deltour.model.User.User;
import com.chatbot.deltour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

//    private UserDTO convertUserDTO(User user) {
//        return UserDTO.builder()
//                .id(user.getIdx())
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .email(user.getEmail())
//                .build();
//    }
}
