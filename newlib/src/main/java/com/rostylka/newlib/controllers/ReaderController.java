package com.rostylka.newlib.controllers;

import com.rostylka.newlib.dto.BookLogDto;
import com.rostylka.newlib.dto.ReaderLogDto;
import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.repositories.BookLogRepository;
import com.rostylka.newlib.services.implementations.BookLogServiceImplementation;
import com.rostylka.newlib.services.implementations.BookServiceImplementation;
import com.rostylka.newlib.services.implementations.ReaderLogServiceImplementation;
import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/readers")
@PreAuthorize("hasAuthority('ROLE_Administrator') || hasAuthority('ROLE_Librarian') || hasAuthority('ROLE_Reader')")
public class ReaderController {

    private UserServiceImplementation userServiceImplementation;
    private BookServiceImplementation bookServiceImplementation;
    private ReaderLogServiceImplementation readerLogServiceImplementation;
    private BookLogServiceImplementation bookLogServiceImplementation;

    /**
     * GET
     * Readers Page for requesting books
     * @param id - ID Reader
     * @param model - Model
     * @return Page for requesting books
     */

    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public String readBooksForRequest(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        model.addAttribute("books", bookServiceImplementation.readAllBooks());
        return "readers/id";
    }

    /**
     * GET
     * Method for viewing all readers Books
     * @param id User ID
     * @param model - Model
     * @return - List of readers Books
     */
    @GetMapping("/{id}/mybooks")
    @PreAuthorize("#id == authentication.principal.id")
    public String readMyBooks(@PathVariable("id") int id, Model model){
        model.addAttribute("readerlogs", readerLogServiceImplementation.
                findByUserAndDateInIsNull(UserMapper.mapToUser(userServiceImplementation.readUserById(id))));
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        return "readers/mybooks";
    }

    /**
     * POST
     * Method for returning Books into Library
     * @param id - reader ID
     * @param readerLogId - Reader Lg ID
     * @return Page with Reader's books
     */
    @PostMapping("{id}/return/{readerLogId}")
    @PreAuthorize("#id == authentication.principal.id")
    public String returnBook(@PathVariable("id") int id, @PathVariable("readerLogId") int readerLogId){
        ReaderLogDto readerLogDto = readerLogServiceImplementation.readReaderLogById(readerLogId);
        readerLogDto.setDateIn(LocalDate.now());
        readerLogServiceImplementation.updateReaderLog(readerLogDto);
        BookLogDto bookLogDto = bookLogServiceImplementation.findBookLogByBook(readerLogDto.getBook());
        bookLogDto.setReadingNumber(bookLogDto.getReadingNumber() - 1);
        bookLogServiceImplementation.updateBookLog(bookLogDto);
        return "redirect:/readers/" + id + "/mybooks";
    }

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @Autowired
    public void setBookServiceImplementation(BookServiceImplementation bookServiceImplementation) {
        this.bookServiceImplementation = bookServiceImplementation;
    }

    @Autowired
    public void setReaderLogServiceImplementation(ReaderLogServiceImplementation readerLogServiceImplementation) {
        this.readerLogServiceImplementation = readerLogServiceImplementation;
    }

    @Autowired
    public void setBookLogServiceImplementation(BookLogServiceImplementation bookLogServiceImplementation) {
        this.bookLogServiceImplementation = bookLogServiceImplementation;
    }
}
