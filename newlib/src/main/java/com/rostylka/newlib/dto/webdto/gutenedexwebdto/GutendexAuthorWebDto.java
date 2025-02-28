package com.rostylka.newlib.dto.webdto.gutenedexwebdto;

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
public class GutendexAuthorWebDto {

    private String name;
    private String birth_year;
    private String death_year;
}
