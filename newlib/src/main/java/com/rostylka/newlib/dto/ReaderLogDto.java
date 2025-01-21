package com.rostylka.newlib.dto;

import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReaderLogDto {

    private int id;
    private User user;
    private Book book;
    private LocalDate dateOut;
    private LocalDate dateIn;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReaderLogDto that)) return false;
        return Objects.equals(user, that.user) && Objects.equals(book, that.book) && Objects.equals(dateOut, that.dateOut) && Objects.equals(dateIn, that.dateIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book, dateOut, dateIn);
    }
}
