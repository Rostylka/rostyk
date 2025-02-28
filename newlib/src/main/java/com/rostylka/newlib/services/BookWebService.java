package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.dto.webdto.gutenedexwebdto.GutendexBookWebDto;

import java.util.List;

/**
 * Interface for getting Book Ifo from API
 */
public interface BookWebService {
    List<BookDto> getAllWebBooks(BookDto bookDto);
}
