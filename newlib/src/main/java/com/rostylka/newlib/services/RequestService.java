package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.RequestDto;

import java.util.List;

/** Request Service interface
 *
 */
public interface RequestService {
    /**
     * Method for creation Request in DataBase
     * @param requestDto - Request DTO
     * @return Request() create new Request in DataBase
     */
    RequestDto createRequest(RequestDto requestDto);

    /**
     * Method for reading all Requests from DataBase
     * @return List of RequestDTO from DataBase
     */
    List<RequestDto> readAllRequests();

    /**
     * Method for reading Request by ID from DataBase
     * @param id ID of Request
     * @return Request by ID
     */
    RequestDto readRequestById(int id);

    /**
     * Method for Updating Request in DataBase
     * @param requestDto - Request DTO
     * @return updated Request
     */
    RequestDto updateRequest(RequestDto requestDto);

    /**
     * Method for Deleting Request from DataBase
     * @param requestDto - Request DTO
     */
    void delete(RequestDto requestDto);
}
