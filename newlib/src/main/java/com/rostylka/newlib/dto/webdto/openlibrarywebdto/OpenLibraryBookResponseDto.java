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
public class OpenLibraryBookResponseDto {
    private String q;
    private List<OpenLibraryBookWebDto> docs;
}
