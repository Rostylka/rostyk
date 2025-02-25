package com.rostylka.newlib.dto;

import com.rostylka.newlib.models.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private int id;
    private String title;
    private List<Author> authors;
    private String summary;
    private String cover;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(title, bookDto.title) && Objects.equals(authors, bookDto.authors)
                && Objects.equals(summary, bookDto.summary) && Objects.equals(cover, bookDto.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, summary, cover);
    }
}
