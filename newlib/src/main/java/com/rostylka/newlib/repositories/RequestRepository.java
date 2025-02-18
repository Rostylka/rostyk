package com.rostylka.newlib.repositories;

import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.models.Request;
import com.rostylka.newlib.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    Request getRequestByUserAndBook(User user, Book book);
}

