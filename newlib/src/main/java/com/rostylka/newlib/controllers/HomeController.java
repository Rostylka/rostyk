package com.rostylka.newlib.controllers;

import com.rostylka.newlib.services.implementations.BookServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    BookServiceImplementation bookServiceImplementation;

    /**
     * Home Page Method
     * @param model - Model
     * @return Home Page
     */
    @GetMapping({"/", "/home", ""})
    public String home(Model model){
        model.addAttribute("books", bookServiceImplementation.readAllBooks());
        return "index";
    }

    @Autowired
    public void setBookServiceImplementation(BookServiceImplementation bookServiceImplementation) {
        this.bookServiceImplementation = bookServiceImplementation;
    }
}
