package com.rostylka.newlib.dto;

import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private LocalDate birthday;
    private Role role;


}
