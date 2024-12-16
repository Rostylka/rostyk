package com.rostylka.newlib.controllers;

import com.rostylka.newlib.dto.AuthorDto;
import com.rostylka.newlib.mappers.AuthorMapper;
import com.rostylka.newlib.mappers.BookMapper;
import com.rostylka.newlib.models.Author;
import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.services.implementations.AuthorServiceImplementation;
import com.rostylka.newlib.services.implementations.BookServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookServiceImplementation bookServiceImplementation;
    private AuthorServiceImplementation authorServiceImplementation;
    private Book newBook;
    private Author newAuthor;

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
    public String newBook(@ModelAttribute("author") Author author, @ModelAttribute("book") Book book,
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
        Set<Author> authorSet = book.getAuthors();
        authorSet.add(author);
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

    @Autowired
    public void setAuthorServiceImplementation(AuthorServiceImplementation authorServiceImplementation) {
        this.authorServiceImplementation = authorServiceImplementation;
    }

    @Autowired
    public void setBookServiceImplementation(BookServiceImplementation bookServiceImplementation) {
        this.bookServiceImplementation = bookServiceImplementation;
    }
}
