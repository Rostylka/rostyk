package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.dto.webdto.gutenedexwebdto.GutendexBookWebDto;
import com.rostylka.newlib.dto.webdto.openlibrarywebdto.OpenLibraryBookWebDto;
import com.rostylka.newlib.mappers.bookwebmappers.GutendexBookWebMapper;
import com.rostylka.newlib.mappers.bookwebmappers.OpenLibraryBookWebMapper;
import com.rostylka.newlib.services.BookWebService;
import com.rostylka.newlib.services.implementations.bookwebserviceimplementations.GutendexBookWebServiceImplementation;
import com.rostylka.newlib.services.implementations.bookwebserviceimplementations.OpenLibraryBookWebServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookWebServiceImplementation implements BookWebService {
    private GutendexBookWebServiceImplementation gutendexBookWebServiceImplementation;
    private OpenLibraryBookWebServiceImplementation openLibraryBookWebServiceImplementation;

    /**
     * Method for getting Book DTOs from all APIs
     * @param bookDto - Book DTO
     * @return List of Book DTOs from all APIs
     */
    @Override
    public List<BookDto> getAllWebBooks(BookDto bookDto) {
        //List<GutendexBookWebDto> gutendexBookWebDtos = gutendexBookWebServiceImplementation.getBooks(bookDto);
        List<OpenLibraryBookWebDto> openLibraryBookWebDtos = openLibraryBookWebServiceImplementation.getBooks(bookDto);
        //List<BookDto> gutendexBooks = GutendexBookWebMapper.mapFromBookWebGutendexDtoListToBookDtoList(gutendexBookWebDtos);
        List<BookDto> openLibraryBooks = OpenLibraryBookWebMapper.mapFromOpenLibraryBookWebDtoListToBookDtoList(openLibraryBookWebDtos);
        return openLibraryBooks;
    }

    @Autowired
    public void setBookWebGutendexServiceImplementation(GutendexBookWebServiceImplementation gutendexBookWebServiceImplementation) {
        this.gutendexBookWebServiceImplementation = gutendexBookWebServiceImplementation;
    }

    @Autowired
    public void setOpenLibraryBookWebServiceImplementation(OpenLibraryBookWebServiceImplementation openLibraryBookWebServiceImplementation) {
        this.openLibraryBookWebServiceImplementation = openLibraryBookWebServiceImplementation;
    }
}
