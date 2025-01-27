package com.rostylka.newlib.controllers;

import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrators")
@PreAuthorize("hasAuthority('ROLE_Administrator')")
public class AdminController {

    private UserServiceImplementation userServiceImplementation;

    /**
     * GET
     * Administration Page
     *
     * @param id    - ID Administrator
     * @param model - Model
     * @return Page for requesting books
     */
    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public String getAdministrationPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        return "administrators/id";
    }

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }
}
