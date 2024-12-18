package com.rostylka.newlib.controllers;

import com.rostylka.newlib.dto.AuthorDto;
import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.mappers.AuthorMapper;
import com.rostylka.newlib.mappers.BookMapper;
import com.rostylka.newlib.models.Author;
import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.services.implementations.AuthorServiceImplementation;
import com.rostylka.newlib.services.implementations.BookServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookServiceImplementation bookServiceImplementation;
    private AuthorServiceImplementation authorServiceImplementation;
    private BookDto bookDto;


    /**
     * GET form
     * CREATE Book
     *
     * @param author - Author
     * @param book   - Book
     * @param model  - Model
     * @return list Books
     */
    @GetMapping("/new")
    public String newBook(@ModelAttribute("author") AuthorDto author, @ModelAttribute("book") Book book,
                          Model model) {
        return "books/new";
    }

    /**
     * GET form
     * CREATE Book
     *
     * @param author - Author
     * @param book   - Book
     * @param model  - Model
     * @return list Books
     */
    @PostMapping("/create")
    public String createBook(@ModelAttribute("author") Author author, @ModelAttribute("book") Book book,
                             Model model) {
        List<Author> authors = book.getAuthors();
        authors.add(author);
        bookServiceImplementation.createBook(BookMapper.mapToBookDto(book));
        return "redirect:/books";
    }

    /**
     * READ ALL books
     *
     * @param model Model
     * @return list of Authors
     */
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("books",
                bookServiceImplementation.readAllBooks());
        return "books/list";
    }

    /**
     * READ book by ID
     * @param id - Book Id
     * @param model Model
     * @return Book by Id
     */
    @GetMapping("/{id}")
    public String readBookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookServiceImplementation.readBookById(id));
        return "books/id";
    }

    /** GET form
     * UPDATE Book by ID
     * @param id - Book Id
     * @param model - Model
     * @return form for Updating Book
     */
    @GetMapping("update/{id}")
    public String readBookForUpdate(@PathVariable("id") int id, @ModelAttribute("author") AuthorDto authorDto,
                                    Model model) {
        model.addAttribute("book", bookServiceImplementation.readBookById(id));
        return "books/update";
    }

    /**POST Update Book TODO this method doesn't work correctly
     * UPDATE Book by ID
     * @param id - path variable ID
     * @param book - Book
     * @return list Books
     */
    @PostMapping("update/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookServiceImplementation.updateBook(BookMapper.mapToBookDto(book));
        return "redirect:/books";
    }

    /** POST
     * Add Author to the AuthorList of Book
     * @param id - Id
     * @param authorDto - Author
     * @return Updated boob
     */
    @PostMapping("update/{id}/addAuthor")
    public String addAuthorToBook(@PathVariable("id") int id, @ModelAttribute("author") AuthorDto authorDto) {
        authorDto.setId(0);
        authorDto = authorServiceImplementation.createAuthor(authorDto);
        bookDto = bookServiceImplementation.readBookById(id);
        bookDto = bookServiceImplementation.addAuthor(bookDto, authorDto);
        bookServiceImplementation.updateBook(bookDto);
        return "redirect:/books/update/{id}";
    }

    @Autowired
    public void setBookServiceImplementation(BookServiceImplementation bookServiceImplementation) {
        this.bookServiceImplementation = bookServiceImplementation;
    }

    @Autowired
    public void setAuthorServiceImplementation(AuthorServiceImplementation authorServiceImplementation) {
        this.authorServiceImplementation = authorServiceImplementation;
    }
}
