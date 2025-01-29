package com.rostylka.newlib.mappers;

import com.rostylka.newlib.dto.UserDto;
import com.rostylka.newlib.dto.UserRegisterDto;

public class UserRegisterDtoMapper {


    /**
     * Method for transforming UserRegisterDto into UserDTo
     * @param userRegisterDto - UserRegisterDto
     * @return userDTO - User DTO
     */
    public static UserDto mapToUserDto(UserRegisterDto userRegisterDto) {
        return new UserDto(0,
                userRegisterDto.getName(),
                userRegisterDto.getSurname(),
                userRegisterDto.getEmail(),
                userRegisterDto.getLogin(),
                userRegisterDto.getPassword(),
                userRegisterDto.getBirthday(),
                null);
        //user.getBooks());
    }

}
