package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.UserDto;

import java.util.List;

/** User Service interface
 *
 */
public interface UserService {
    /**
     * Method for creation User in DataBase
     * @param userDto
     * @return User() create new User in DataBase
     */
    UserDto createUser(UserDto userDto);

    /**
     * Method for reading all Users from DataBase
     * @return List of UserDTO from DataBase
     */
    List<UserDto> readAllUsers();
}
