package com.rostylka.newlib.dto.webdto.gutenedexwebdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Book Data form API DTO class
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GutendexBookWebDto {

    private String title;
    private List<GutendexAuthorWebDto> authors;
    private List<String> summaries;
    private Map<String, String> formats;


}
