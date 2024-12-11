package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.AuthorDto;

import java.util.List;

/** Author Service interface
 *
 */
public interface AuthorService {
    /**
     * Method for creation Author in DataBase
     * @param authorDto - Author DTO
     * @return Author() create new Author in DataBase
     */
    AuthorDto createAuthor(AuthorDto authorDto);

    /**
     * Method for reading all Authors from DataBase
     * @return List of AuthorDTO from DataBase
     */
    List<AuthorDto> readAllAuthors();

    /**
     * Method for reading Author by ID from DataBase
     * @param id ID of Author
     * @return Author by ID
     */
    AuthorDto readAuthorById(int id);

    /**
     * Method for Updating Author in DataBase
     * @param authorDto - Author DTO
     * @return updated Author
     */
    AuthorDto updateAuthor(AuthorDto authorDto);

    /**
     * Method for Deleting Author from DataBase
     * @param authorDto - Author DTO
     */
    void delete(AuthorDto authorDto);
}
