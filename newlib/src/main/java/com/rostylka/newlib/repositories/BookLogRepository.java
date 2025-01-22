package com.rostylka.newlib.repositories;

import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.models.BookLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLogRepository extends JpaRepository<BookLog, Integer> {
    BookLog findBookLogByBook(Book book);
}
