package com.rostylka.newlib.dto;

import com.rostylka.newlib.models.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookLogDto {

    private int id;
    private Book book;
    private int totalNumber;
    private int readingNumber;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BookLogDto that)) return false;
        return totalNumber == that.totalNumber && readingNumber == that.readingNumber && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, totalNumber, readingNumber);
    }
}
