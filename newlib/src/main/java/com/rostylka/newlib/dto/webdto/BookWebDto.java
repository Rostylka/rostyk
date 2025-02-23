package com.rostylka.newlib.dto.webdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

//TODO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookWebDto {

    private String title;
    private List<AuthorWebDto> authors;
    private List<String> summaries;
    private Map<String, String> formats;


}
