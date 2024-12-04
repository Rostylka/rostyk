package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
