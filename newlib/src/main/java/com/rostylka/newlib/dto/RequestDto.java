package com.rostylka.newlib.dto;

import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    private int id;
    private Book book;
    private User user;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RequestDto that = (RequestDto) o;
        return Objects.equals(book, that.book) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, user);
    }
}
