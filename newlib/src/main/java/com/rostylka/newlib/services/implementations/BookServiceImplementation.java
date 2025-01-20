package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.AuthorDto;
import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.mappers.AuthorMapper;
import com.rostylka.newlib.mappers.BookMapper;
import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.repositories.BookRepository;
import com.rostylka.newlib.services.BookService;
import com.rostylka.newlib.services.BookService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImplementation implements BookService {


    private BookRepository bookRepository;

    /**
     * Constructor
     * @param bookRepository - Book Repository
     */
    public BookServiceImplementation(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    /**
     * Method for creation Book in DataBase
     * @param bookDto - Book DTO
     * @return Book() create new Book in DataBase
     */
    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = BookMapper.mapToBook(bookDto);
        Book createdBook = bookRepository.save(book);
        return BookMapper.mapToBookDto(createdBook);
    }

    /**
     * Method for reading all Books from DataBase
     * @return List of BookDTO from DataBase
     */
    @Override
    public List<BookDto> readAllBooks() {
        return BookMapper.mapToBookDtoList(bookRepository.findAll());
    }

    /**
     * Method for reading Book by ID from DataBase
     * @param id ID of Book
     * @return Book by ID
     */
    @Override
    public BookDto readBookById(int id) {
        return BookMapper.mapToBookDto(bookRepository.getReferenceById(id));
    }

    /**
     * Method for Updating Book in DataBase
     * @param bookDto
     * update Book in DataBase
     */
    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book updatedBook = bookRepository.getReferenceById(bookDto.getId());
        updatedBook.setTitle(bookDto.getTitle());
        updatedBook.setAuthors(bookDto.getAuthors());
        return BookMapper.mapToBookDto(bookRepository.save(updatedBook));
    }

    /**
     * Method for adding author to book
     * @param bookDto - bookDTO
     * @param authorDto - authorDTO
     * @return Book DTO with added Author
     */
    public BookDto addAuthor(BookDto bookDto, AuthorDto authorDto) {
        bookDto.getAuthors().add(AuthorMapper.mapToAuthor(authorDto));
        return bookDto;
    }

    /**
     * Method for Deleting Book from DataBase
     * @param bookDto - Book DTO
     */
    @Override
    public void delete(BookDto bookDto) {
        bookRepository.delete(BookMapper.mapToBook(bookDto));
    }
}
