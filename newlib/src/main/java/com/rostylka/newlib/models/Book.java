package com.rostylka.newlib.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_author"))
    private Set<Author> authors = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "users_books",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthors(), book.getAuthors()) && Objects.equals(getUsers(), book.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthors(), getUsers());
    }
}
