package com.rostylka.newlib.dto.webdto.openlibrarywebdto;

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
public class OpenLibraryAuthorWebDto {

    private String name;
    private String birth_date;
    private String death_date;
    private String key;
}
