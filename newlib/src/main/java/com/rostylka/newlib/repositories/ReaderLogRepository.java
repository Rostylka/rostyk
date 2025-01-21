package com.rostylka.newlib.repositories;

import com.rostylka.newlib.models.ReaderLog;
import com.rostylka.newlib.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReaderLogRepository extends JpaRepository<ReaderLog, Integer> {
    List<ReaderLog> findByUser(User user);
    List<ReaderLog> findByUserAndDateInIsNull(User user);
}
