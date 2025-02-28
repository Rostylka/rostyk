package com.rostylka.newlib.dto.webdto.gutenedexwebdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * Class for transferring Book Data from API
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GutendexBookResponseDto {
     private String count;
     private String next;
     private String previous;
     private List<GutendexBookWebDto> results;
}
