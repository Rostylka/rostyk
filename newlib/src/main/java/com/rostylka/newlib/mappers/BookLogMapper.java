package com.rostylka.newlib.mappers;

import com.rostylka.newlib.dto.BookLogDto;
import com.rostylka.newlib.models.BookLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for transforming BookLog into BookLogDTO and  vice versa
 */
public class BookLogMapper {
    /**
     * Method for transforming BookLog into BookLogDTo
     * @param bookLog - BookLog
     * @return BookLogDTO - BookLog DTO
     */
    public static BookLogDto mapToBookLogDto(BookLog bookLog) {
        return new BookLogDto(bookLog.getId(),
                bookLog.getBook(),
                bookLog.getTotalNumber(),
                bookLog.getReadingNumber());
    }

    /**
     * Method for transforming BookLogDTO into BookLog
     * @param bookLogDto - BookLog DTO
     * @return BookLog - BookLog
     */
    public static BookLog mapToBookLog(BookLogDto bookLogDto) {
        return new BookLog(bookLogDto.getId(),
                bookLogDto.getBook(),
                bookLogDto.getTotalNumber(),
                bookLogDto.getReadingNumber());
    }

    /**
     * Method for transforming BookLog list into BookLog DTO list
     * @param bookLogs list of BookLogs
     * @return list of BookLogs DTO
     */
    public static List<BookLogDto> mapToBookLogDtoList(List<BookLog> bookLogs) {
        List<BookLogDto> dtoBookLogs = new ArrayList<>();
        for (BookLog bookLog : bookLogs) {
            dtoBookLogs.add(BookLogMapper.mapToBookLogDto(bookLog));
        }
        return dtoBookLogs;
    }

    /**
     * Method for transforming BookLog DTO list into BookLog list
     * @param dtoBookLogs list of BookLogs
     * @return list of BookLogs
     */
    public static List<BookLog> mapToBookLogList(List<BookLogDto> dtoBookLogs) {
        List<BookLog> bookLogs = new ArrayList<>();
        for (BookLogDto dtoBookLog : dtoBookLogs) {
            bookLogs.add(BookLogMapper.mapToBookLog(dtoBookLog));
        }
        return bookLogs;
    }
}
