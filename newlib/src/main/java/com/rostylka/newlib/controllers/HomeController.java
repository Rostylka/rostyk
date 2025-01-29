package com.rostylka.newlib.controllers;

import com.rostylka.newlib.services.implementations.BookServiceImplementation;
import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private BookServiceImplementation bookServiceImplementation;
    private UserServiceImplementation userServiceImplementation;

    /**
     * GET
     * Home Page Method
     * @param model - Model
     * @return Home Page
     */
    @GetMapping({"/", "/home", ""})
    public String home(Model model){
        model.addAttribute("books", bookServiceImplementation.readAllBooks());
        model.addAttribute("link", userServiceImplementation.createLink());
        model.addAttribute("isAuthenticated", userServiceImplementation.getUserDetail()!=null);
        return "index";
    }

    @Autowired
    public void setBookServiceImplementation(BookServiceImplementation bookServiceImplementation) {
        this.bookServiceImplementation = bookServiceImplementation;
    }

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }
}
