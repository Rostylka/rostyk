package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.RequestDto;
import com.rostylka.newlib.mappers.RequestMapper;
import com.rostylka.newlib.models.Request;
import com.rostylka.newlib.repositories.RequestRepository;
import com.rostylka.newlib.services.RequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImplementation implements RequestService {


    private RequestRepository requestRepository;

    /**
     * Constructor
     * @param requestRepository - Request Repository
     */
    public RequestServiceImplementation(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
    }

    /**
     * Method for creation Request in DataBase
     * @param requestDto - Request DTO
     * @return Request() create new Request in DataBase
     */
    @Override
    public RequestDto createRequest(RequestDto requestDto) {
        Request request = RequestMapper.mapToRequest(requestDto);
        Request createdRequest = requestRepository.save(request);
        return RequestMapper.mapToRequestDto(createdRequest);
    }

    /**
     * Method for reading all Requests from DataBase
     * @return List of RequestDTO from DataBase
     */
    @Override
    public List<RequestDto> readAllRequests() {
        return RequestMapper.mapToRequestDtoList(requestRepository.findAll());
    }

    /**
     * Method for reading Request by ID from DataBase
     * @param id ID of Request
     * @return Request by ID
     */
    @Override
    public RequestDto readRequestById(int id) {
        return RequestMapper.mapToRequestDto(requestRepository.getReferenceById(id));
    }

    /**
     * Method for Updating Request in DataBase
     * @param requestDto
     * update Request in DataBase
     */
    @Override
    public RequestDto updateRequest(RequestDto requestDto) {
        Request updatedRequest = requestRepository.getReferenceById(requestDto.getId());
        updatedRequest.setBook(requestDto.getBook());
        updatedRequest.setUser(requestDto.getUser());
        return RequestMapper.mapToRequestDto(requestRepository.save(updatedRequest));
    }

    /**
     * Method for Deleting Request from DataBase
     * @param requestDto - Request DTO
     */
    @Override
    public void delete(RequestDto requestDto) {
        requestRepository.delete(RequestMapper.mapToRequest(requestDto));
    }
}
