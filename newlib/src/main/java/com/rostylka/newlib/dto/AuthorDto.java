package com.rostylka.newlib.dto;

import com.rostylka.newlib.models.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private int id;
    private String name;
    private String surname;
    private Set<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorDto)) return false;
        AuthorDto authorDto = (AuthorDto) o;
        return Objects.equals(getName(), authorDto.getName()) && Objects.equals(getSurname(), authorDto.getSurname()) && Objects.equals(getBooks(), authorDto.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getBooks());
    }
}


