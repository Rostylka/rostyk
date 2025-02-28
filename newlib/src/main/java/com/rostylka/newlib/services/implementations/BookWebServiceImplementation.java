package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.dto.webdto.gutenedexwebdto.GutendexBookWebDto;
import com.rostylka.newlib.mappers.bookwebmappers.GutendexBookWebMapper;
import com.rostylka.newlib.services.BookWebService;
import com.rostylka.newlib.services.implementations.bookwebserviceimplementations.BookWebGutendexServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookWebServiceImplementation implements BookWebService {
    private BookWebGutendexServiceImplementation bookWebGutendexServiceImplementation;

    /**
     * Method for getting Book DTOs from all APIs
     * @param bookDto - Book DTO
     * @return List of Book DTOs from all APIs
     */
    @Override
    public List<BookDto> getAllWebBooks(BookDto bookDto) {
        List<GutendexBookWebDto> books = bookWebGutendexServiceImplementation.getBooks(bookDto);
        return GutendexBookWebMapper.mapFromBookWebGutendexDtoListToBookDtoList(books);
    }

    @Autowired
    public void setBookWebGutendexServiceImplementation(BookWebGutendexServiceImplementation bookWebGutendexServiceImplementation) {
        this.bookWebGutendexServiceImplementation = bookWebGutendexServiceImplementation;
    }
}
