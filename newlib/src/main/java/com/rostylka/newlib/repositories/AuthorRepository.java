package com.rostylka.newlib.repositories;

import com.rostylka.newlib.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author getAuthorByNameAndSurname(String name, String surname);
}

