package com.rostylka.newlib.dto.webdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for transferring Author Data from API
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorWebDto {

    private String name;
    private String birth_year;
    private String death_year;
}
