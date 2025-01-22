package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.BookLogDto;
import com.rostylka.newlib.mappers.BookLogMapper;
import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.models.BookLog;
import com.rostylka.newlib.repositories.BookLogRepository;
import com.rostylka.newlib.services.BookLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookLogServiceImplementation implements BookLogService {


    private BookLogRepository bookLogRepository;

    /**
     * Constructor
     * @param bookLogRepository - BookLog Repository
     */
    public BookLogServiceImplementation(BookLogRepository bookLogRepository){
        this.bookLogRepository = bookLogRepository;
    }

    /**
     * Method for creation BookLog in DataBase
     * @param bookLogDto - BookLog DTO
     * @return BookLog() create new BookLog in DataBase
     */
    @Override
    public BookLogDto createBookLog(BookLogDto bookLogDto) {
        BookLog bookLog = BookLogMapper.mapToBookLog(bookLogDto);
        BookLog createdBookLog = bookLogRepository.save(bookLog);
        return BookLogMapper.mapToBookLogDto(createdBookLog);
    }

    /**
     * Method for reading all BookLogs from DataBase
     * @return List of BookLogDTO from DataBase
     */
    @Override
    public List<BookLogDto> readAllBookLogs() {
        return BookLogMapper.mapToBookLogDtoList(bookLogRepository.findAll());
    }

    /**
     * Method for reading BookLog by ID from DataBase
     * @param id ID of BookLog
     * @return BookLog by ID
     */
    @Override
    public BookLogDto readBookLogById(int id) {
        return BookLogMapper.mapToBookLogDto(bookLogRepository.getReferenceById(id));
    }

    /**
     * Method for Updating BookLog in DataBase
     * @param bookLogDto
     * update BookLog in DataBase
     */
    @Override
    public BookLogDto updateBookLog(BookLogDto bookLogDto) {
        BookLog updatedBookLog = bookLogRepository.getReferenceById(bookLogDto.getId());
        updatedBookLog.setBook(bookLogDto.getBook());
        updatedBookLog.setTotalNumber(bookLogDto.getTotalNumber());
        updatedBookLog.setReadingNumber(bookLogDto.getReadingNumber());
        return BookLogMapper.mapToBookLogDto(bookLogRepository.save(updatedBookLog));
    }

    /**
     * Method for Deleting BookLog from DataBase
     * @param bookLogDto - BookLog DTO
     */
    @Override
    public void delete(BookLogDto bookLogDto) {
        bookLogRepository.delete(BookLogMapper.mapToBookLog(bookLogDto));
    }

    /**
     * Method for finding Book Log by Book
     * @param book - Book
     */
    public BookLogDto findBookLogByBook(Book book){
        return BookLogMapper.mapToBookLogDto(bookLogRepository.findBookLogByBook(book));
    }
}
