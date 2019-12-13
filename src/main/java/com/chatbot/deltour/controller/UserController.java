package com.chatbot.deltour.controller;

import com.chatbot.deltour.anotation.SocialUser;
import com.chatbot.deltour.model.User.User;
import com.chatbot.deltour.sevice.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/user")
    public List<User> listUser() {
        return userService.findAll();
    }

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("user")
    public String delete(@PathVariable String id) {
        userService.delete(id);
        return "sucess";
    }

    @GetMapping("/loginSucess")
    public String loginComplete(@SocialUser User user) {
        return "redirect:/chat";
    }

    @GetMapping("/loginFail")
    public String loginFail(@SocialUser User user) {
        return "fail";
    }

}