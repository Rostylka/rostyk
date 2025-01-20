package com.rostylka.newlib.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column (name = "login", nullable = false, length = 20)
    private String login;

    @Column (name = "password", nullable = true, length = 20)
    private String password;

    @Column(name = "birthday", nullable = true)
    private LocalDate birthday;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_role")
    private Role role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", role=" + role +
                '}';
    }
}



