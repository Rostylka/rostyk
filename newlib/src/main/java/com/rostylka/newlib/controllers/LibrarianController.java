package com.rostylka.newlib.controllers;

import com.rostylka.newlib.dto.BookLogDto;
import com.rostylka.newlib.dto.RequestDto;
import com.rostylka.newlib.mappers.BookMapper;
import com.rostylka.newlib.mappers.ReaderLogMapper;
import com.rostylka.newlib.models.ReaderLog;
import com.rostylka.newlib.services.implementations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/librarians")
@PreAuthorize("hasAuthority('ROLE_Administrator') || hasAuthority('ROLE_Librarian')")
public class LibrarianController {

    private UserServiceImplementation userServiceImplementation;
    private RequestServiceImplementation requestServiceImplementation;
    private ReaderLogServiceImplementation readerLogServiceImplementation;
    private BookLogServiceImplementation bookLogServiceImplementation;
    private BookServiceImplementation bookServiceImplementation;

    /**
     * GET
     * Librarian Page for managing books
     *
     * @param id    - ID Librarian
     * @param model - Model
     * @return Page for requesting books
     */
    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public String readBookRequests(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        model.addAttribute("requests", requestServiceImplementation.readAllRequests());
        model.addAttribute("link", userServiceImplementation.createLink());
        return "librarians/id";
    }

    /**
     * POST
     * Method for confirming Request and then deleting it
     *
     * @param id        - User ID
     * @param requestId - Request ID
     * @return to the main Librarian Page
     */
    @PostMapping("/{id}/confirm/{requestId}")
    @PreAuthorize("#id == authentication.principal.id")
    public String confirmRequest(@PathVariable("id") int id, @PathVariable("requestId") int requestId) {
        RequestDto requestDto = requestServiceImplementation.readRequestById(requestId);
        ReaderLog readerLog = new ReaderLog();
        readerLog.setUser(requestDto.getUser());
        readerLog.setBook(requestDto.getBook());
        readerLog.setDateOut(LocalDate.now());
        BookLogDto bookLogDto = bookLogServiceImplementation.findBookLogByBook(requestDto.getBook());
        bookLogDto.setReadingNumber(bookLogDto.getReadingNumber() + 1);
        bookLogServiceImplementation.updateBookLog(bookLogDto);
        readerLogServiceImplementation.createReaderLog(ReaderLogMapper.mapToReaderLogDto(readerLog));
        requestServiceImplementation.delete(requestDto);
        return "redirect:/librarians/" + id;
    }

    /**
     * POST
     * Method for deleting Request
     *
     * @param id        - User ID
     * @param requestId - Request ID
     * @return to the main Librarian Page
     */
    @PostMapping("/{id}/delete/{requestId}")
    @PreAuthorize("#id == authentication.principal.id")
    public String deleteRequest(@PathVariable("id") int id, @PathVariable("requestId") int requestId) {
        RequestDto requestDto = requestServiceImplementation.readRequestById(requestId);
        requestServiceImplementation.delete(requestDto);
        return "redirect:/librarians/" + id;
    }

    /**
     * GET
     * Method for viewing all Reader Logs
     *
     * @param id    - User Id
     * @param model - Model
     * @return Page with all Logs
     */
    @GetMapping("/{id}/readerlogs")
    @PreAuthorize("#id == authentication.principal.id")
    public String viewAllReaderLogs(@PathVariable("id") int id, Model model) {
        model.addAttribute("readerlogs", readerLogServiceImplementation.readAllReaderLogs());
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        model.addAttribute("link", userServiceImplementation.createLink());
        return "librarians/readerlogs";
    }

    /**
     * GET
     * Method for viewing all Book Logs
     *
     * @param id    - User Id
     * @param model - Model
     * @return Page with all Logs
     */
    @GetMapping("/{id}/booklogs")
    @PreAuthorize("#id == authentication.principal.id")
    public String viewAllBookLogs(@PathVariable("id") int id, Model model) {
        model.addAttribute("booklogs", bookLogServiceImplementation.readAllBookLogs());
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        model.addAttribute("link", userServiceImplementation.createLink());
        return "librarians/booklogs";
    }

    /**
     * GET
     * Method for registering Book into Library
     *
     * @param id    - User Id
     * @param model - Model
     * @return Page with all Logs
     */
    @GetMapping("/{id}/register")
    @PreAuthorize("#id == authentication.principal.id")
    public String registerBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("books", bookServiceImplementation.readAllBooks());
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        model.addAttribute("link", userServiceImplementation.createLink());
        return "librarians/register";
    }

    /**
     * GET
     * Method for getting register form of Book into Library
     *
     * @param id    - User Id
     * @param model - Model
     * @return Registration Page of Book
     */
    @GetMapping("/{id}/register/{bookId}")
    @PreAuthorize("#id == authentication.principal.id")
    public String getRegisterFormOfBook(@PathVariable("id") int id, @PathVariable("bookId") int bookId,
                                        @ModelAttribute("booklog") BookLogDto bookLogDto,
                                        Model model) {
        model.addAttribute("book", bookServiceImplementation.readBookById(bookId));
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        model.addAttribute("link", userServiceImplementation.createLink());
        return "booklogs/id";
    }

    /**
     * POST
     * Method for Confirming registration of Book into Library
     * @param id - ID User
     * @param bookId - ID Book
     * @param bookLogDto - Book Log DTO
     * @return Registration Page
     */
    @PostMapping("/{id}/register/{bookId}")
    @PreAuthorize("#id == authentication.principal.id")
    public String confirmBookRegistration(@PathVariable("id") int id,
                                          @PathVariable("bookId") int bookId,
                                          @ModelAttribute("booklog") BookLogDto bookLogDto) {
        bookLogDto.setBook(BookMapper.mapToBook(bookServiceImplementation.readBookById(bookId)));
        bookLogDto.setReadingNumber(0);
        bookLogDto.setId(0);
        bookLogServiceImplementation.createBookLog(bookLogDto);
        return "redirect:/librarians/" + id + "/register";
    }



    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @Autowired
    public void setRequestServiceImplementation(RequestServiceImplementation requestServiceImplementation) {
        this.requestServiceImplementation = requestServiceImplementation;
    }

    @Autowired
    public void setReaderLogServiceImplementation(ReaderLogServiceImplementation readerLogServiceImplementation) {
        this.readerLogServiceImplementation = readerLogServiceImplementation;
    }

    @Autowired
    public void setBookLogServiceImplementation(BookLogServiceImplementation bookLogServiceImplementation) {
        this.bookLogServiceImplementation = bookLogServiceImplementation;
    }

    @Autowired
    public void setBookServiceImplementation(BookServiceImplementation bookServiceImplementation) {
        this.bookServiceImplementation = bookServiceImplementation;
    }
}