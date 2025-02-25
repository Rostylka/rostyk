package com.rostylka.newlib.mappers;

import com.rostylka.newlib.dto.AuthorDto;
import com.rostylka.newlib.dto.AuthorDto;
import com.rostylka.newlib.dto.webdto.AuthorWebDto;
import com.rostylka.newlib.models.Author;
import com.rostylka.newlib.models.Author;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for transforming Author into AuthorDTO and  vice versa
 */
public class AuthorMapper {
    /**
     * Method for transforming Author into AuthorDTo
     * @param author - Author
     * @return AuthorDTO - Author DTO
     */
    public static AuthorDto mapToAuthorDto(Author author) {
        return new AuthorDto(author.getId(),
                author.getName(),
                author.getSurname(),
                author.getBooks());
    }

    /**
     * Method for transforming AuthorDTO into Author
     * @param authorDto - Author DTO
     * @return Author - Author
     */
    public static Author mapToAuthor(AuthorDto authorDto) {
        return new Author(authorDto.getId(),
                authorDto.getName(),
                authorDto.getSurname(),
                authorDto.getBooks());
    }

    /**
     * Method for transforming Author list into Author DTO list
     * @param authors list of Authors
     * @return list of Authors DTO
     */
    public static List<AuthorDto> mapToAuthorDtoList(List<Author> authors) {
        List<AuthorDto> dtoAuthors = new ArrayList<>();
        for (Author author : authors) {
            dtoAuthors.add(AuthorMapper.mapToAuthorDto(author));
        }
        return dtoAuthors;
    }

    /**
     * Method for transforming Author DTO list into Author list
     * @param dtoAuthors list of Authors
     * @return list of Authors
     */
    public static List<Author> mapToAuthorList(List<AuthorDto> dtoAuthors) {
        List<Author> authors = new ArrayList<>();
        for (AuthorDto dtoAuthor : dtoAuthors) {
            authors.add(AuthorMapper.mapToAuthor(dtoAuthor));
        }
        return authors;
    }

    /**
     * Method for transforming List of AuthorWeb DTO to List of Authors
     * @param webAuthors - List of Authors Web DTOs from API
     * @return List of Authors
     */
    public static List<Author> mapFromAuthorWebDtoToAuthorList(List<AuthorWebDto> webAuthors){
        List<Author> authors = new ArrayList<>();
        for (AuthorWebDto webAuthor : webAuthors) {
            List<String> names = List.of(webAuthor.getName().split(", "));
            Author author = new Author();
            author.setName(names.get(1));
            author.setSurname(names.get(0));
            authors.add(author);
        }
        return authors;
    }
}
