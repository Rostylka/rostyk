package com.rostylka.newlib.dto.webdto.openlibrarywebdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Book Data form API DTO class
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenLibraryBookWebDto {
    String title;
    List<String> author_name;
    List<String> author_key;
    String cover_edition_key;
}
