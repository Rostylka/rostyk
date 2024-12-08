package com.rostylka.newlib.controllers;

import com.rostylka.newlib.dto.UserDto;
import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.models.User;
import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserServiceImplementation userServiceImplementation;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("users",
                userServiceImplementation.readAllUsers());
        return "users/list";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        userServiceImplementation.createUser(UserMapper.mapToUserDto(user));
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }
}
