package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.BookDto;
import com.rostylka.newlib.dto.webdto.BookWebDto;

import java.util.List;

public interface BookWebService {
    List<BookWebDto> getBooks(BookDto bookDto);
}
