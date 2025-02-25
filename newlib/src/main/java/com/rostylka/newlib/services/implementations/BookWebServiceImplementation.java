package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.dto.webdto.BookResponseDto;
import com.rostylka.newlib.dto.webdto.BookWebDto;
import com.rostylka.newlib.services.BookWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Class for getting Books from https://gutendex.com/ public API
 */
@Service
public class BookWebServiceImplementation implements BookWebService {
    private WebClient webClient;

    /**
     * Method for getting info of the Book from API
     * @param bookDto
     * @return
     */
    @Override
    public List<BookWebDto> getBooks(BookDto bookDto) {
        String uri = "/books/?search=" + bookDto.getTitle()
                + " " + bookDto.getAuthors().get(0).getName()
                + " " + bookDto.getAuthors().get(0).getSurname();
        String data = webClient.get()
                .uri(uri).retrieve().bodyToMono(String.class).block();
        BookResponseDto bookResponseDto = webClient.get()
                .uri(uri).retrieve().bodyToMono(BookResponseDto.class).block();
        return bookResponseDto.getResults();
    }

    @Autowired
    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }
}
