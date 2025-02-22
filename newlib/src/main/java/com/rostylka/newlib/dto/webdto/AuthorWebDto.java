package com.rostylka.newlib.dto.webdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorWebDto {

    private String name;
    private int birth_year;
    private int death_year;
}
