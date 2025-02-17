package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.AuthorDto;
import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.dto.BookLogDto;
import com.rostylka.newlib.mappers.AuthorMapper;
import com.rostylka.newlib.mappers.BookMapper;
import com.rostylka.newlib.models.Author;
import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.models.BookLog;
import com.rostylka.newlib.repositories.BookRepository;
import com.rostylka.newlib.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class BookServiceImplementation implements BookService {


    private final BookRepository bookRepository;
    private BookLogServiceImplementation bookLogServiceImplementation;

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
        if (!checkIfBookPresent(bookDto)) {
            Book createdBook = bookRepository.save(BookMapper.mapToBook(bookDto));
            return BookMapper.mapToBookDto(createdBook);
        }
        return getBookByAuthorsAndTitle(bookDto.getAuthors(), bookDto.getTitle());
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

    /**
     * Method for finding book by title
     * @param title Book's title
     * @return List of Book DTOs by Title
     */
    public List<BookDto> getBookByTitle(String title) {
        Pattern pattern = getPattern(title);
        List<BookDto> books = readAllBooks();
        List<BookDto> booksByTitle = new ArrayList<>();
        for (BookDto book : books) {
            Matcher matcher = pattern.matcher(book.getTitle().toLowerCase());
            if (matcher.find()) {
                booksByTitle.add(book);
            }
        }
        return booksByTitle;
    }

    /**
     *
     * @param name Author's name
     * @param surname Author's surname
     * @return List of DTOs by Author
     */
    public List<BookDto> getBookByAuthor(String name, String surname) {
        List<BookDto> booksByAuthor = new LinkedList<>();
        List<BookDto> books = readAllBooks();
        Pattern namePattern = getPattern(name);
        Pattern surnamePattern = getPattern(surname);
        for (BookDto book : books) {
            for (Author author : book.getAuthors()) {
                Matcher nameMatcher = namePattern.matcher(author.getName().toLowerCase());
                Matcher surnameMatcher = surnamePattern.matcher(author.getSurname().toLowerCase());
                if ((nameMatcher.find() && surnameMatcher.find()) ||
                        (nameMatcher.find() && surname.isBlank()) ||
                        (surnameMatcher.find() && name.isBlank())) {
                    booksByAuthor.add(book);
                    break;
                }
            }
        }
        return booksByAuthor;
    }

    /**
     * Method for creating Pattern for searching book by title or by author
     * @param name - name fo pattern
     * @return pattern for sorting books, authors etc.
     */
    private Pattern getPattern(String name) {
        return Pattern.compile(name.toLowerCase().trim().replaceAll("\\s{2,}", " "));
    }

    /**
     * Method for checking if Book is present in Database
     * @param bookDto - Book DTO
     * @return true if Book is present in Data Base
     */
    public boolean checkIfBookPresent(BookDto bookDto) {
        List<BookDto> books  = readAllBooks();
        for (BookDto book: books) {
            if (book.getTitle().equals(bookDto.getTitle()) && book.getAuthors().contains(bookDto.getAuthors().get(0))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method for getting Book by Authors and Title
     * @param authors - List of Authors
     * @param title - title of the Book
     * @return Book DTO with these Authors and Title
     */
    public BookDto getBookByAuthorsAndTitle(List <Author> authors, String title) {
        return BookMapper.mapToBookDto(bookRepository.getBookByAuthorsAndTitle(authors, title));
    }

    /**
     * Method for finding Unregistered Books
     * @return List Book DTO of Unregistered Books
     */
    public List<BookDto> findUnregisteredBooks() {
        List<BookDto> loggedBooks = bookLogServiceImplementation.readAllBookLogs().stream()
                .map(bookLogDto -> BookMapper.mapToBookDto(bookLogDto.getBook()))
                .toList();
        return readAllBooks().stream().filter(bookDto -> !loggedBooks.contains(bookDto)).toList();
    }

    @Autowired
    public void setBookLogServiceImplementation(BookLogServiceImplementation bookLogServiceImplementation) {
        this.bookLogServiceImplementation = bookLogServiceImplementation;
    }
}
