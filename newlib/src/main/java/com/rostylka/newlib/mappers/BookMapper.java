package com.rostylka.newlib.mappers;

import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.models.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for transforming Book into BookDTO and  vice versa
 */
public class BookMapper {
    /**
     * Method for transforming Book into BookDTo
     * @param book - Book
     * @return BookDTO - Book DTO
     */
    public static BookDto mapToBookDto(Book book) {
        return new BookDto(book.getId(),
                book.getTitle(),
                book.getAuthors());
                //book.getUsers());
    }

    /**
     * Method for transforming BookDTO into Book
     * @param bookDto - Book DTO
     * @return Book - Book
     */
    public static Book mapToBook(BookDto bookDto) {
        return new Book(bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthors());
                //bookDto.getUsers());
    }

    /**
     * Method for transforming Book list into Book DTO list
     * @param books list of Books
     * @return list of Books DTO
     */
    public static List<BookDto> mapToBookDtoList(List<Book> books) {
        List<BookDto> dtoBooks = new ArrayList<>();
        for (Book book : books) {
            dtoBooks.add(BookMapper.mapToBookDto(book));
        }
        return dtoBooks;
    }

    /**
     * Method for transforming Book DTO list into Book list
     * @param dtoBooks list of Books
     * @return list of Books
     */
    public static List<Book> mapToBookList(List<BookDto> dtoBooks) {
        List<Book> books = new ArrayList<>();
        for (BookDto dtoBook : dtoBooks) {
            books.add(BookMapper.mapToBook(dtoBook));
        }
        return books;
    }
}