package com.rostylka.newlib.mappers;

import com.rostylka.newlib.dto.UserDto;
import com.rostylka.newlib.models.User;

/**
 * Class for transforming User into UserDTO and  vice versa
 */
public class UserMapper {

    /**
     * Method for transforming User into UserDTo
     * @param user
     * @return userDTO
     */
    public static UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword(),
                user.getBirthday(),
                user.getRole(),
                user.getBooks());
    }

    /**
     * Method for transforming UserDTO into User
     * @param userDto
     * @return user
     */
    public static User mapToUser(UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getBirthday(),
                userDto.getRole(),
                userDto.getBooks());
    }
}
