package com.chatbot.deltour.sevice;

import com.chatbot.deltour.dto.request.UserDTO;
import com.chatbot.deltour.model.User.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    User findByEmail(String email);

    void delete(String id);
}
