package com.rostylka.newlib.controllers;


import com.rostylka.newlib.dto.RequestDto;
import com.rostylka.newlib.mappers.BookMapper;
import com.rostylka.newlib.mappers.UserMapper;
import com.rostylka.newlib.services.implementations.BookServiceImplementation;
import com.rostylka.newlib.services.implementations.RequestServiceImplementation;
import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/requests")
public class RequestController {

    BookServiceImplementation bookServiceImplementation;
    UserServiceImplementation userServiceImplementation;
    RequestServiceImplementation requestServiceImplementation;

    @PostMapping("/add/{bookId}/{userId}")
    public String addRequest(@PathVariable("bookId") int bookId, @PathVariable("userId") int userId){
        RequestDto requestDto = new RequestDto();
        requestDto.setBook(BookMapper.mapToBook(bookServiceImplementation.readBookById(bookId)));
        requestDto.setUser(UserMapper.mapToUser(userServiceImplementation.readUserById(userId)));
        requestServiceImplementation.createRequest(requestDto);
        return "redirect:/readers/" + userId;
    }

    @Autowired
    public void setRequestServiceImplementation(RequestServiceImplementation requestServiceImplementation) {
        this.requestServiceImplementation = requestServiceImplementation;
    }

    @Autowired
    public void setBookServiceImplementation(BookServiceImplementation bookServiceImplementation) {
        this.bookServiceImplementation = bookServiceImplementation;
    }

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }
}
