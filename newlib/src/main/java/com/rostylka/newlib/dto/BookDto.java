package com.rostylka.newlib.dto;

import com.rostylka.newlib.models.Author;
import com.rostylka.newlib.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private int id;
    private String title;
    private Set<Author> authors;
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto)) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(getTitle(), bookDto.getTitle()) && Objects.equals(getAuthors(), bookDto.getAuthors()) && Objects.equals(getUsers(), bookDto.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthors(), getUsers());
    }
}
