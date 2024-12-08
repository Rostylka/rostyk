package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> readAllUsers();
}
