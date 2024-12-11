package com.rostylka.newlib.dto;

import com.rostylka.newlib.models.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private int id;
    private String name;
    private String surname;
    private List<Book> books;
}
