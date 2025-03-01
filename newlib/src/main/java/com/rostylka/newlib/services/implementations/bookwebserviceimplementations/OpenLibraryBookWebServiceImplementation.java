package com.rostylka.newlib.services.implementations.bookwebserviceimplementations;

import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.dto.webdto.openlibrarywebdto.OpenLibraryBookResponseDto;
import com.rostylka.newlib.dto.webdto.openlibrarywebdto.OpenLibraryBookWebDto;
import com.rostylka.newlib.mappers.bookwebmappers.OpenLibraryBookWebMapper;
import com.rostylka.newlib.services.BookWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Class for getting Books from https://openlibrary.org/ public API
 */
@Service
public class OpenLibraryBookWebServiceImplementation implements BookWebService {
    private WebClient webClientOpenLibrary;

    /**
     * Method for getting all books from API     *
     *
     * @param bookDto - Book DTO
     * @return - List of Book DTOs from API
     */
    @Override
    public List<BookDto> getAllWebBooks(BookDto bookDto) {
        return OpenLibraryBookWebMapper.mapFromOpenLibraryBookWebDtoListToBookDtoList(getBooks(bookDto));
    }

    /**
     * Method for getting info of the Book from API
     *
     * @param bookDto Book DTO for searching
     * @return List BookWeb DTO
     */
    public List<OpenLibraryBookWebDto> getBooks(BookDto bookDto) {
        String uri = "/search.json?q=" + bookDto.getTitle()
                + " " + bookDto.getAuthors().get(0).getName()
                + " " + bookDto.getAuthors().get(0).getSurname();
        OpenLibraryBookResponseDto openLibraryBookResponseDto = webClientOpenLibrary
                .get()
                .uri(uri).retrieve().bodyToMono(OpenLibraryBookResponseDto.class).block();
        return openLibraryBookResponseDto.getDocs();
    }

    @Autowired
    public void setWebClientOpenLibrary(WebClient webClientOpenLibrary) {
        this.webClientOpenLibrary = webClientOpenLibrary;
    }
}
