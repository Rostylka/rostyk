package com.rostylka.newlib.mappers.bookwebmappers;

import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.dto.webdto.openlibrarywebdto.OpenLibraryBookWebDto;
import com.rostylka.newlib.models.Author;

import java.util.ArrayList;
import java.util.List;

public class OpenLibraryBookWebMapper {
    /**
     * Method for transforming BookWeb DTO to Book DTO
     * @param openLibraryBookWebDto - OpenLibraryBookWebDtoBookWeb DTO
     * @return Book DTO
     */
    public static BookDto mapFromOpenLibraryDtoToBookDto(OpenLibraryBookWebDto openLibraryBookWebDto) {
        return new BookDto(0,
                (openLibraryBookWebDto.getTitle() != null)? openLibraryBookWebDto.getTitle():null,
                mapFromAuthorOpenLibraryWebDtoToAuthorList(openLibraryBookWebDto.getAuthor_name()),
                null,
                (openLibraryBookWebDto.getCover_edition_key() != null)
                        ? "https://covers.openlibrary.org/b/olid/"
                                + openLibraryBookWebDto.getCover_edition_key() + "-M.jpg"
                        :null);
    }

    /**
     * Method for transforming BookWeb DTO list into BookDto list
     * @param dtoWebBooks list of Books
     * @return list of Book DTOs
     */
    public static List<BookDto> mapFromOpenLibraryBookWebDtoListToBookDtoList(List<OpenLibraryBookWebDto> dtoWebBooks) {
        List<BookDto> books = new ArrayList<>();
        for (OpenLibraryBookWebDto dtoWebBook : dtoWebBooks) {
            books.add(mapFromOpenLibraryDtoToBookDto(dtoWebBook));
        }
        return books;
    }

    /**
     * Method for transforming List of AOpenLibraryBookWebDtoAuthorWeb DTO to List of Authors
     * @param webAuthors - List of Authors Web DTOs from API
     * @return List of Authors
     */
    public static List<Author> mapFromAuthorOpenLibraryWebDtoToAuthorList(List<String> webAuthors){
        if (webAuthors == null) {
            return new ArrayList<>();
        }
        List<Author> authors = new ArrayList<>();
        for (String webAuthor : webAuthors) {
            String[] names = webAuthor.split(" ", 2);
            Author author = new Author();
            author.setName(names[0]);
            author.setSurname(names.length > 1? names[1]: "");
            authors.add(author);
        }
        return authors;
    }
}
