package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.BookLogDto;

import java.util.List;

/** BookLog Service interface
 *
 */
public interface BookLogService {
    /**
     * Method for creation BookLog in DataBase
     * @param bookLogDto - BookLog DTO
     * @return BookLog() create new BookLog in DataBase
     */
    BookLogDto createBookLog(BookLogDto bookLogDto);

    /**
     * Method for reading all BookLogs from DataBase
     * @return List of BookLogDTO from DataBase
     */
    List<BookLogDto> readAllBookLogs();

    /**
     * Method for reading BookLog by ID from DataBase
     * @param id ID of BookLog
     * @return BookLog by ID
     */
    BookLogDto readBookLogById(int id);

    /**
     * Method for Updating BookLog in DataBase
     * @param bookLogDto - BookLog DTO
     * @return updated BookLog
     */
    BookLogDto updateBookLog(BookLogDto bookLogDto);

    /**
     * Method for Deleting BookLog from DataBase
     * @param bookLogDto - BookLog DTO
     */
    void delete(BookLogDto bookLogDto);
}
