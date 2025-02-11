package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.AuthorDto;
import com.rostylka.newlib.mappers.AuthorMapper;
import com.rostylka.newlib.models.Author;
import com.rostylka.newlib.models.Book;
import com.rostylka.newlib.repositories.AuthorRepository;
import com.rostylka.newlib.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorServiceImplementation implements AuthorService {


    private AuthorRepository authorRepository;

    /**
     * Constructor
     * @param authorRepository - Author Repository
     */
    public AuthorServiceImplementation(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    /**
     * Method for creation Author in DataBase
     * @param authorDto - Author DTO
     * @return Author() create new Author in DataBase
     */
    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        if (!checkIfAuthorPresent(authorDto)) {
            Author author = AuthorMapper.mapToAuthor(authorDto);
            Author createdAuthor = authorRepository.save(author);
            return AuthorMapper.mapToAuthorDto(createdAuthor);
        }
        return getAuthorByNameAndSurname(authorDto.getName(), authorDto.getSurname());
    }

    /**
     * Method for reading all Authors from DataBase
     * @return List of AuthorDTO from DataBase
     */
    @Override
    public List<AuthorDto> readAllAuthors() {
        return AuthorMapper.mapToAuthorDtoList(authorRepository.findAll());
    }

    /**
     * Method for reading Author by ID from DataBase
     * @param id ID of Author
     * @return Author by ID
     */
    @Override
    public AuthorDto readAuthorById(int id) {
        return AuthorMapper.mapToAuthorDto(authorRepository.getReferenceById(id));
    }

    /**
     * Method for Updating Author in DataBase
     * @param authorDto
     * update Author in DataBase
     */
    @Override
    public AuthorDto updateAuthor(AuthorDto authorDto) {
        Author updatedAuthor = authorRepository.getReferenceById(authorDto.getId());
        updatedAuthor.setName(authorDto.getName());
        updatedAuthor.setSurname(authorDto.getSurname());
        updatedAuthor.setBooks(authorDto.getBooks());
        return AuthorMapper.mapToAuthorDto(authorRepository.save(updatedAuthor));
    }

    /**
     * Method for Deleting Author from DataBase
     * @param authorDto - Author DTO
     */
    @Override
    public void delete(AuthorDto authorDto) {
            Set<Book> books = readAuthorById(authorDto.getId()).getBooks();
            if (books.isEmpty()) {
            authorRepository.delete(AuthorMapper.mapToAuthor(authorDto));
            }
    }

    /**
     * Method for getting Author by name and surname
     * @param name - Author's name
     * @param surname - Author's surname
     * @return Author object
     */
    public AuthorDto getAuthorByNameAndSurname(String name, String surname) {
        return AuthorMapper.mapToAuthorDto(authorRepository.getAuthorByNameAndSurname(name, surname));
    }

    /**
     * Method for checking if author is present in Database
     * @param authorDto - Author DTO
     * @return true if Author is present in Data Base
     */
    public boolean checkIfAuthorPresent(AuthorDto authorDto) {
        for(AuthorDto author: readAllAuthors()) {
            if (author.getName().equals(authorDto.getName()) &&
            author.getSurname().equals(authorDto.getSurname())) {
                return true;
            }
        }
        return false;
    }
}