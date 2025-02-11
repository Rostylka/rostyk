package com.rostylka.newlib.repositories;

import com.rostylka.newlib.models.Author;
import com.rostylka.newlib.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book getBookByAuthorsAndTitle(List<Author> authors, String title);
}

