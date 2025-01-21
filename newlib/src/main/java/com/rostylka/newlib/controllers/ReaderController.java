package com.rostylka.newlib.controllers;

import com.rostylka.newlib.dto.ReaderLogDto;
import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.services.implementations.BookServiceImplementation;
import com.rostylka.newlib.services.implementations.ReaderLogServiceImplementation;
import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/readers")
public class ReaderController {

    UserServiceImplementation userServiceImplementation;
    BookServiceImplementation bookServiceImplementation;
    private ReaderLogServiceImplementation readerLogServiceImplementation;

    /** GET
     * Readers Page for requesting books
     * @param id - ID Reader
     * @param model - Model
     * @return Page for requesting books
     */
    @GetMapping("/{id}")
    public String readBooksForRequest(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        model.addAttribute("books", bookServiceImplementation.readAllBooks());
        return "readers/id";
    }

    /**
     * Method for viewing all readers Books
     * @param id User ID
     * @param model - Model
     * @return - List of readers Books
     */
    @GetMapping("/{id}/mybooks")
    public String readMyBooks(@PathVariable("id") int id, Model model){
        model.addAttribute("readerlogs", readerLogServiceImplementation.
                findByUserAndDateInIsNull(UserMapper.mapToUser(userServiceImplementation.readUserById(id))));
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        return "readers/mybooks";
    }

    /** POST
     * Method for returning Books into Library
     * @param id - reader ID
     * @param readerLogId - Reader Lg ID
     * @return Page with Reader's books
     */
    @PostMapping("{id}/return/{readerLogId}")
    public String returnBook(@PathVariable("id") int id, @PathVariable("readerLogId") int readerLogId){
        ReaderLogDto readerLogDto = readerLogServiceImplementation.readReaderLogById(readerLogId);
        readerLogDto.setDateIn(LocalDate.now());
        readerLogServiceImplementation.updateReaderLog(readerLogDto);
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
}
