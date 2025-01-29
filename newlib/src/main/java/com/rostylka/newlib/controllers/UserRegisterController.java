package com.rostylka.newlib.controllers;

import com.rostylka.newlib.dto.UserDto;
import com.rostylka.newlib.dto.UserRegisterDto;
import com.rostylka.newlib.mappers.RoleMapper;
import com.rostylka.newlib.mappers.UserRegisterDtoMapper;
import com.rostylka.newlib.models.User;
import com.rostylka.newlib.repositories.UserRepository;
import com.rostylka.newlib.services.implementations.RoleServiceImplementation;
import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegisterController {

    private UserServiceImplementation userServiceImplementation;
    private RoleServiceImplementation roleServiceImplementation;
    private UserRepository userRepository;

    /**
     * GET form
     * Method for getting registration form
     * @param userRegisterDto - userRegister DTO
     * @return registration page
     */
    @GetMapping("/register")
    public String newUser(@ModelAttribute("user") UserRegisterDto userRegisterDto){
        return "registers/registration";
    }

    /**
     * POST
     * Method for registration User or return Registration form if errors occurred
     * @param userRegisterDto - userRegister DTO
     * @param result - Error Result
     * @return - Home Page if everything OK or Registration Form
     */
    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute("user") UserRegisterDto userRegisterDto,
                                  BindingResult result){
        if (userRepository.findByLogin(userRegisterDto.getLogin()) != null) {
            result.addError(new FieldError("user", "login",
                    "Login is already used"));
        }
        if(!userRegisterDto.getConfirmedPassword().equals(userRegisterDto.getPassword())) {
            result.addError(new FieldError("user", "confirmedPassword",
                    "Password and Confirmed Password doesn't match"));
        }
        if (result.hasErrors()) {
            return "registers/registration";
        }
        UserDto userDto = UserRegisterDtoMapper.mapToUserDto(userRegisterDto);
        userDto.setRole(RoleMapper.mapToRole(roleServiceImplementation.getByRoleName("Reader")));
        userServiceImplementation.createUser(userDto);
        return "redirect:/home";
    }

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @Autowired
    public void setRoleServiceImplementation(RoleServiceImplementation roleServiceImplementation) {
        this.roleServiceImplementation = roleServiceImplementation;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
