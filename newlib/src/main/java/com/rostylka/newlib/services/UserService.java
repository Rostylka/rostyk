package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/** User Service interface
 *
 */
public interface UserService extends UserDetailsService {
    /**
     * Method for creation User in DataBase
     * @param userDto - User DTO
     * @return User() create new User in DataBase
     */
    UserDto createUser(UserDto userDto);

    /**
     * Method for reading all Users from DataBase
     * @return List of UserDTO from DataBase
     */
    List<UserDto> readAllUsers();

    /**
     * Method for reading User by ID from DataBase
     * @param id ID of User
     * @return User by ID
     */
    UserDto readUserById(int id);

    /**
     * Method for Updating User in DataBase
     * @param userDto - User DTO
     * @return updated User
     */
    UserDto updateUser(UserDto userDto);

    /**
     * Method for Deleting User from DataBase
     * @param userDto - User DTO
     */
    void delete(UserDto userDto);
}
