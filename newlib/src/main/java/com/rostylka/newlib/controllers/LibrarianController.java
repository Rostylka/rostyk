package com.rostylka.newlib.controllers;

import com.rostylka.newlib.dto.RequestDto;
import com.rostylka.newlib.mappers.ReaderLogMapper;
import com.rostylka.newlib.models.ReaderLog;
import com.rostylka.newlib.services.implementations.ReaderLogServiceImplementation;
import com.rostylka.newlib.services.implementations.RequestServiceImplementation;
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
@RequestMapping("/librarians")
public class LibrarianController {

    private UserServiceImplementation userServiceImplementation;
    private RequestServiceImplementation requestServiceImplementation;
    private ReaderLogServiceImplementation readerLogServiceImplementation;

    /**
     * GET
     * Librarian Page for managing books
     *
     * @param id    - ID Librarian
     * @param model - Model
     * @return Page for requesting books
     */
    @GetMapping("/{id}")
    public String readBookRequests(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        model.addAttribute("requests", requestServiceImplementation.readAllRequests());
        return "librarians/id";
    }

    /** POST
     * Method for confirming Request and then deleting it
     * @param id - User ID
     * @param requestId - Request ID
     * @return to the main Librarian Page
     */
    @PostMapping("/{id}/confirm/{requestId}")
    public String confirmRequest(@PathVariable("id") int id, @PathVariable("requestId") int requestId){
        RequestDto requestDto = requestServiceImplementation.readRequestById(requestId);
        ReaderLog readerLog = new ReaderLog();
        readerLog.setUser(requestDto.getUser());
        readerLog.setBook(requestDto.getBook());
        readerLog.setDateOut(LocalDate.now());
        readerLogServiceImplementation.createReaderLog(ReaderLogMapper.mapToReaderLogDto(readerLog));
        requestServiceImplementation.delete(requestDto);
        return "redirect:/librarians/" + id;
    }

    /** POST
     * Method for deleting Request
     * @param id - User ID
     * @param requestId - Request ID
     * @return to the main Librarian Page
     */
    @PostMapping("/{id}/delete/{requestId}")
    public String deleteRequest(@PathVariable("id") int id, @PathVariable("requestId") int requestId){
        RequestDto requestDto = requestServiceImplementation.readRequestById(requestId);
        requestServiceImplementation.delete(requestDto);
        return "redirect:/librarians/" + id;
    }

    /** GET
     * Method for viewing all Reader Logs
     * @param id - User Id
     * @param model - Model
     * @return Page with all Logs
     */
    @GetMapping("/{id}/readerlogs")
    public String viewAllReaderLogs(@PathVariable("id") int id, Model model){
        model.addAttribute("readerlogs", readerLogServiceImplementation.readAllReaderLogs());
        model.addAttribute("user", userServiceImplementation.readUserById(id));
        return "librarians/readerlogs";
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
}
