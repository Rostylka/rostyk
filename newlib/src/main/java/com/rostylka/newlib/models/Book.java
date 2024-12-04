package com.rostylka.newlib.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @ManyToMany()
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_author"))
    private List<Author> authors;

    @ManyToMany()
    @JoinTable(name = "users_books",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> users;


}
