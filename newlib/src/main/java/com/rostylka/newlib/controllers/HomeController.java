package com.rostylka.newlib.controllers;

import com.rostylka.newlib.services.implementations.BookServiceImplementation;
import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private BookServiceImplementation bookServiceImplementation;
    private UserServiceImplementation userServiceImplementation;

    /**
     * GET
     * Home Page Method
     * @param model - Model
     * @return Home Page
     */
    @GetMapping({"/", "/home", ""})
    public String home(Model model){
        model.addAttribute("books", bookServiceImplementation.readAllBooks());
        model.addAttribute("link", userServiceImplementation.createLink());
        model.addAttribute("isAuthenticated", userServiceImplementation.getUserDetail()!=null);
        return "index";
    }

    /**
     * GET
     * Method for getting books by title
     * @param title - title of the Book
     * @param model - Model
     * @return Home Page with books by title
     */
    @GetMapping("/booksbytitle")
    public String findBookByTitle(@RequestParam("title") String title, Model model) {
        model.addAttribute("books", bookServiceImplementation.getBookByTitle(title));
        model.addAttribute("link", userServiceImplementation.createLink());
        model.addAttribute("isAuthenticated", userServiceImplementation.getUserDetail()!=null);
        return "index";
    }

    /**
     * GET
     * Method for getting books by Author
     * @param name - Author's name
     * @param surname - Author's surname
     * @param model - Model
     * @return Home Page with books by author
     */
    @GetMapping("/booksbyauthor")
    public String findBookByAuthor(@RequestParam("name") String name,
                                   @RequestParam("surname") String surname, Model model) {
        model.addAttribute("books", bookServiceImplementation.getBookByAuthor(name, surname));
        model.addAttribute("link", userServiceImplementation.createLink());
        model.addAttribute("isAuthenticated", userServiceImplementation.getUserDetail()!=null);
        return "index";
    }

    @Autowired
    public void setBookServiceImplementation(BookServiceImplementation bookServiceImplementation) {
        this.bookServiceImplementation = bookServiceImplementation;
    }

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }
}
