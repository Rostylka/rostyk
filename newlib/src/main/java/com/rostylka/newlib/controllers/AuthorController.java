package com.rostylka.newlib.controllers;

import com.rostylka.newlib.mappers.AuthorMapper;
import com.rostylka.newlib.models.Author;
import com.rostylka.newlib.services.implementations.AuthorServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
//@PreAuthorize("hasAuthority('ROLE_Administrator') || hasAnyAuthority('ROLE_Librarian')")
public class AuthorController {

    private AuthorServiceImplementation authorServiceImplementation;

    /**
     * GET form
     * CREATE Author
     *
     * @param author Author
     * @return list Authors
     */
    @GetMapping("/new")
    public String newAuthor(@ModelAttribute("author") Author author, Model model) {
        return "authors/new";
    }

    /**
     * POST Author
     * CREATE Author
     *
     * @param author Author
     * @return list Authors
     */
    @PostMapping("/create")
    public String createAuthor(@ModelAttribute("author") Author author) {
        authorServiceImplementation.createAuthor(AuthorMapper.mapToAuthorDto(author));
        return "redirect:/authors";
    }

    /**
     * READ ALL Authors
     *
     * @param model Model
     * @return list of Authors
     */
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("authors",
                authorServiceImplementation.readAllAuthors());
        return "authors/list";
    }

    /**
     * READ Author by ID
     * @param id - Author Id
     * @param model Model
     * @return Author by Id
     */
    @GetMapping("/{id}")
    public String readAuthorById(@PathVariable("id") int id, Model model) {
        model.addAttribute("author", authorServiceImplementation.readAuthorById(id));
        return "authors/id";
    }

    /** GET form
     * UPDATE Author by ID
     * @param id - Author Id
     * @param model - Model
     * @return form for Updating Author
     */
    @GetMapping("update/{id}")
    public String readAuthorForUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("author", authorServiceImplementation.readAuthorById(id));
        return "authors/update";
    }

    /**POST Update Author
     * UPDATE Author by ID
     * @param id - path variable ID
     * @param author - Author
     * @return list Authors
     */
    @PostMapping("update/{id}")
    public String updateRole(@PathVariable("id") int id, @ModelAttribute("author") Author author) {
        authorServiceImplementation.updateAuthor(AuthorMapper.mapToAuthorDto(author));
        return "redirect:/authors";
    }

    /**
     * DELETE Author by ID
     * @param id - path variable ID
     * @param author - Author
     * @return list Authors
     */
    @PostMapping("delete/{id}")
    public String deleteAuthor(@PathVariable("id") int id, @ModelAttribute("author") Author author) {
        authorServiceImplementation.delete(AuthorMapper.mapToAuthorDto(author));
        return "redirect:/authors";
    }

    @Autowired
    public void setAuthorServiceImplementation(AuthorServiceImplementation authorServiceImplementation) {
        this.authorServiceImplementation = authorServiceImplementation;
    }
}
