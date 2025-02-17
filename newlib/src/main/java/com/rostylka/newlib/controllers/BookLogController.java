package com.rostylka.newlib.controllers;

import com.rostylka.newlib.dto.BookLogDto;
import com.rostylka.newlib.services.implementations.BookLogServiceImplementation;
import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/booklogs")
@PreAuthorize("hasAuthority('ROLE_Administrator') || hasAuthority('ROLE_Librarian')")
public class BookLogController {

    private BookLogServiceImplementation bookLogServiceImplementation;
    private UserServiceImplementation userServiceImplementation;

    /**
     * GET
     * Method for getting form for updating Book Log
     * @param id - BookLog ID
     * @param model - Model
     * @return Page for updating Book Log
     */
    @GetMapping("{id}/update")
    public String readBookLogForUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("bookLog", bookLogServiceImplementation.readBookLogById(id));
        model.addAttribute("link", userServiceImplementation.createLink());
        return "booklogs/update";
    }

    /**
     * POST
     * Method for updating Book Logs ()changing total numbers of books)
     * @param id - Book Log ID
     * @param bookLogDto - BookLog DTO
     * @return to BookLog Page with updated BookLog
     */
    @PostMapping("/{id}/update")
    public String updateBookLog(@PathVariable("id") int id, @ModelAttribute("bookLog") BookLogDto bookLogDto){
        BookLogDto updatedBookLog = bookLogServiceImplementation.readBookLogById(id);
        updatedBookLog.setTotalNumber(bookLogDto.getTotalNumber());
        bookLogServiceImplementation.updateBookLog(updatedBookLog);
        return "redirect:/librarians/" + userServiceImplementation.getUserDetail().getId() + "/booklogs";
    }

    @Autowired
    public void setBookLogServiceImplementation(BookLogServiceImplementation bookLogServiceImplementation) {
        this.bookLogServiceImplementation = bookLogServiceImplementation;
    }

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }
}
