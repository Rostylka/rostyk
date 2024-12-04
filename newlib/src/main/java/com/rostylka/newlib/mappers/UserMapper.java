package com.rostylka.newlib.mappers;

import com.rostylka.newlib.dto.UserDto;
import com.rostylka.newlib.models.User;

public class UserMapper {
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
