package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.BookDto;

import java.util.List;

/** Book Service interface
 *
 */
public interface BookService {
    /**
     * Method for creation Book in DataBase
     * @param bookDto - Book DTO
     * @return Book() create new Book in DataBase
     */
    BookDto createBook(BookDto bookDto);

    /**
     * Method for reading all Books from DataBase
     * @return List of BookDTO from DataBase
     */
    List<BookDto> readAllBooks();

    /**
     * Method for reading Book by ID from DataBase
     * @param id ID of Book
     * @return Book by ID
     */
    BookDto readBookById(int id);

    /**
     * Method for Updating Book in DataBase
     * @param bookDto - Book DTO
     * @return updated Book
     */
    BookDto updateBook(BookDto bookDto);

    /**
     * Method for Deleting Book from DataBase
     * @param bookDto - Book DTO
     */
    void delete(BookDto bookDto);
}