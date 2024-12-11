package com.rostylka.newlib.dto;

import com.rostylka.newlib.models.Author;
import com.rostylka.newlib.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private int id;
    private String title;
    private List<Author> authors;
    private List<User> users;
}
