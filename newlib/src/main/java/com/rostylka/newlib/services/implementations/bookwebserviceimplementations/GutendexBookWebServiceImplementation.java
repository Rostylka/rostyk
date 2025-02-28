package com.rostylka.newlib.services.implementations.bookwebserviceimplementations;

import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.dto.webdto.gutenedexwebdto.GutendexBookResponseDto;
import com.rostylka.newlib.dto.webdto.gutenedexwebdto.GutendexBookWebDto;
import com.rostylka.newlib.mappers.bookwebmappers.GutendexBookWebMapper;
import com.rostylka.newlib.services.BookWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Class for getting Books from https://gutendex.com/ public API
 */
@Service
public class GutendexBookWebServiceImplementation implements BookWebService {
    private WebClient webClientGutendex;

    /**
     * Method for getting all books from API
     * @param bookDto - Book DTO
     * @return - List of Book DTOs from API
     */
    @Override
    public List<BookDto> getAllWebBooks(BookDto bookDto) {
        return GutendexBookWebMapper.mapFromBookWebGutendexDtoListToBookDtoList(getBooks(bookDto));
    }

    /**
     * Method for getting info of the Book from API
     * @param bookDto Book DTO for searching
     * @return List BookWeb DTO
     */
    public List<GutendexBookWebDto> getBooks(BookDto bookDto) {
        String uri = "/books/?search=" + bookDto.getTitle()
                + " " + bookDto.getAuthors().get(0).getName()
                + " " + bookDto.getAuthors().get(0).getSurname();
        GutendexBookResponseDto gutendexBookResponseDto = webClientGutendex.get()
                .uri(uri).retrieve().bodyToMono(GutendexBookResponseDto.class).block();
        return gutendexBookResponseDto.getResults();
    }

    @Autowired
    public void setWebClientGutendex(WebClient webClientGutendex) {
        this.webClientGutendex = webClientGutendex;
    }
}
