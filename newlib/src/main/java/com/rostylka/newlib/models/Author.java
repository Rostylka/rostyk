package com.rostylka.newlib.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToMany()
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "id_author"),
            inverseJoinColumns = @JoinColumn(name = "id_book"))
    private List<Book> books;
}
