package com.rostylka.newlib.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_user", nullable = false)
    private int id;

    @Column (name = "name", nullable = true, length = 20)
    private String name;

    @Column (name = "surname", nullable = true, length = 20)
    private String surname;

    @Column (name = "email", nullable = true, length = 20)
    private String email;

    @Column (name = "login", nullable = true, length = 20)
    private String login;

    @Column (name = "password", nullable = true, length = 20)
    private String password;

    @Column(name = "birthday", nullable = true)
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @ManyToMany()
    @JoinTable(name = "users_books",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_book"))
    private List<Book> books;



}



