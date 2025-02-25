package com.rostylka.newlib.mappers;

import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.dto.webdto.BookWebDto;
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
                book.getAuthors(), book.getSummary(), book.getCover());
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
                bookDto.getAuthors(),
                bookDto.getSummary(),
                bookDto.getCover());
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

    /**
     * Method for transforming BookWed DTO to Book DTO
     * @param bookWebDto - BookWeb DTO
     * @return Book DTO
     */
    public static BookDto mapFromBookWebDtoToBookDto(BookWebDto bookWebDto) {
        return new BookDto(0,
                (bookWebDto.getTitle() != null)?bookWebDto.getTitle():null,
                AuthorMapper.mapFromAuthorWebDtoToAuthorList(bookWebDto.getAuthors()),
                (!bookWebDto.getSummaries().isEmpty())?bookWebDto.getSummaries().get(0):null,
                (bookWebDto.getFormats().get("image/jpeg") != null)?bookWebDto.getFormats().get("image/jpeg"):null);
    }

    /**
     * Method for transforming BookWeb DTO list into BookDto list
     * @param dtoWebBooks list of Books
     * @return list of Book DTOs
     */
    public static List<BookDto> mapFromBookWebDtoListToBookDtoList(List<BookWebDto> dtoWebBooks) {
        List<BookDto> books = new ArrayList<>();
        for (BookWebDto dtoWebBook : dtoWebBooks) {
            books.add(BookMapper.mapFromBookWebDtoToBookDto(dtoWebBook));
        }
        return books;
    }
}