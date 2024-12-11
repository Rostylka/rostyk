package com.rostylka.newlib.controllers;

import com.rostylka.newlib.dto.RoleDto;
import com.rostylka.newlib.mappers.RoleMapper;
import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.models.Role;
import com.rostylka.newlib.models.User;
import com.rostylka.newlib.services.implementations.RoleServiceImplementation;
import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserServiceImplementation userServiceImplementation;
    private RoleServiceImplementation roleServiceImplementation;

    /**
     * GET form
     * CREATE User
     *
     * @param user User
     * @return list Users
     */
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        getAllRoles(model);
        return "users/new";
    }

    /**
     * POST User
     * CREATE User
     *
     * @param user User
     * @return list Users
     */
    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        userServiceImplementation.createUser(UserMapper.mapToUserDto(user));
        return "redirect:/users";
    }

    /**
     * READ ALL Users
     *
     * @param model Model
     * @return list of Users
     */
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("users",
                userServiceImplementation.readAllUsers());
        return "users/list";
    }

    /**
     * READ User by ID
     * @param id - User Id
     * @param model Model
     * @return User by Id
     */
    @GetMapping("/{id}")
    public String readUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        return "users/id";
    }

    /** GET form
     * UPDATE role by ID
     * @param id - User Id
     * @param model - Model
     * @return form for Updating User
     */
    @GetMapping("update/{id}")
    public String readUserForUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        getAllRoles(model);
        return "users/update";
    }

    /**POST Update User
     * UPDATE User by ID
     * @param id - path variable ID
     * @param user - User
     * @return list Users
     */
    @PostMapping("update/{id}")
    public String updateRole(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        userServiceImplementation.updateUser(UserMapper.mapToUserDto(user));
        return "redirect:/users";
    }

    /**
     * DELETE User by ID
     * @param id - path variable ID
     * @param user - User
     * @return list Roles
     */
    @PostMapping("delete/{id}")
    public String deleteRole(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        userServiceImplementation.delete(UserMapper.mapToUserDto(user));
        return "redirect:/users";
    }

    /**
     * Servise method for getting all Roles an adding them as Model Attribute
     * @param model Model
     */
    private void getAllRoles(Model model) {
        List<Role> roles = new ArrayList<>();
        List<RoleDto> dtoRoles = roleServiceImplementation.readAllRoles();
        for (RoleDto roleDto : dtoRoles) {
            roles.add(RoleMapper.mapToRole(roleDto));
        }
        model.addAttribute("roles",
                roles);
    }

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @Autowired
    public void setRoleServiceImplementation(RoleServiceImplementation roleServiceImplementation) {
        this.roleServiceImplementation = roleServiceImplementation;
    }
}
