package com.rostylka.newlib.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "readerlogs")
public class ReaderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_readerlog", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;

    @Column(name = "data_out", nullable = true)
    private LocalDate dateOut;

    @Column(name = "date_in", nullable = true)
    private LocalDate dateIn;

}
