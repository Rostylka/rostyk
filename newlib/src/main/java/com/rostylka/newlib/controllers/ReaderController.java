package com.rostylka.newlib.controllers;

import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.services.implementations.BookServiceImplementation;
import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/readers")
public class ReaderController {

    UserServiceImplementation userServiceImplementation;
    BookServiceImplementation bookServiceImplementation;

    @GetMapping("/{id}")
    public String readUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        model.addAttribute("books", bookServiceImplementation.readAllBooks());
        return "readers/id";
    }

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @Autowired
    public void setBookServiceImplementation(BookServiceImplementation bookServiceImplementation) {
        this.bookServiceImplementation = bookServiceImplementation;
    }
}
