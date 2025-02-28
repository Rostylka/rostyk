package com.rostylka.newlib.mappers.bookwebmappers;

import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.dto.webdto.gutenedexwebdto.GutendexAuthorWebDto;
import com.rostylka.newlib.dto.webdto.gutenedexwebdto.GutendexBookWebDto;
import com.rostylka.newlib.models.Author;

import java.util.ArrayList;
import java.util.List;

public class GutendexBookWebMapper {
    /**
     * Method for transforming GutendexBookWeb DTO to Book DTO
     * @param gutendexBookWebDto - GutendexBookWeb DTO
     * @return Book DTO
     */
    public static BookDto mapFromBookWebGutendexDtoToBookDto(GutendexBookWebDto gutendexBookWebDto) {
        return new BookDto(0,
                (gutendexBookWebDto.getTitle() != null)? gutendexBookWebDto.getTitle():null,
                mapFromAuthorGutendexWebDtoToAuthorList(gutendexBookWebDto.getAuthors()),
                (!gutendexBookWebDto.getSummaries().isEmpty())? gutendexBookWebDto.getSummaries().get(0):null,
                (gutendexBookWebDto.getFormats().get("image/jpeg") != null)? gutendexBookWebDto.getFormats().get("image/jpeg"):null);
    }

    /**
     * Method for transforming BookWeb DTO list into BookDto list
     * @param dtoWebBooks list of Books
     * @return list of Book DTOs
     */
    public static List<BookDto> mapFromBookWebGutendexDtoListToBookDtoList(List<GutendexBookWebDto> dtoWebBooks) {
        List<BookDto> books = new ArrayList<>();
        for (GutendexBookWebDto dtoWebBook : dtoWebBooks) {
            books.add(mapFromBookWebGutendexDtoToBookDto(dtoWebBook));
        }
        return books;
    }

    /**
     * Method for transforming List of AGutendexAuthorWeb DTO to List of Authors
     * @param webAuthors - List of Authors Web DTOs from API
     * @return List of Authors
     */
    public static List<Author> mapFromAuthorGutendexWebDtoToAuthorList(List<GutendexAuthorWebDto> webAuthors){
        List<Author> authors = new ArrayList<>();
        for (GutendexAuthorWebDto webAuthor : webAuthors) {
            List<String> names = List.of(webAuthor.getName().split(", "));
            Author author = new Author();
            author.setName(names.size() > 1? names.get(1): names.get(0));
            author.setSurname(names.size() > 1? names.get(0): "");
            authors.add(author);
        }
        return authors;
    }
}
