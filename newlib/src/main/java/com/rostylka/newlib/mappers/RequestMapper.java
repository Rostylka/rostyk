package com.rostylka.newlib.mappers;

import com.rostylka.newlib.dto.RequestDto;
import com.rostylka.newlib.models.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for transforming Request into RequestDTO and  vice versa
 */
public class RequestMapper {
    /**
     * Method for transforming Request into RequestDTo
     * @param request - Request
     * @return RequestDTO - Request DTO
     */
    public static RequestDto mapToRequestDto(Request request) {
        return new RequestDto(request.getId(),
                request.getBook(),
                request.getUser());
    }

    /**
     * Method for transforming RequestDTO into Request
     * @param requestDto - Request DTO
     * @return Request - Request
     */
    public static Request mapToRequest(RequestDto requestDto) {
        return new Request(requestDto.getId(),
                requestDto.getBook(),
                requestDto.getUser());
    }

    /**
     * Method for transforming Request list into Request DTO list
     * @param requests list of Requests
     * @return list of Requests DTO
     */
    public static List<RequestDto> mapToRequestDtoList(List<Request> requests) {
        List<RequestDto> dtoRequests = new ArrayList<>();
        for (Request request : requests) {
            dtoRequests.add(RequestMapper.mapToRequestDto(request));
        }
        return dtoRequests;
    }

    /**
     * Method for transforming Request DTO list into Request list
     * @param dtoRequests list of Requests
     * @return list of Requests
     */
    public static List<Request> mapToRequestList(List<RequestDto> dtoRequests) {
        List<Request> requests = new ArrayList<>();
        for (RequestDto dtoRequest : dtoRequests) {
            requests.add(RequestMapper.mapToRequest(dtoRequest));
        }
        return requests;
    }
}
