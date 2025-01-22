package com.rostylka.newlib.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class fo storing information about total number of Books
 * and number of reading Books *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booklogs")
public class BookLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booklog", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;

    /**
     * total number of books
     */
    @Column(name = "total_number")
    private int totalNumber;

    /**
     * Number of books that are reading now
     */
    @Column(name = "reading_number")
    private int readingNumber;






}
