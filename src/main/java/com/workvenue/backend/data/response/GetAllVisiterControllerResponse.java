package com.workvenue.backend.data.response;

import com.workvenue.backend.data.dto.GetVisiterDTO;
import com.workvenue.backend.data.other.RestHeader;

import java.util.List;

public class GetAllVisiterControllerResponse extends BaseControllerResponse{

    List<GetVisiterDTO> getVisiterDTOList;

    public GetAllVisiterControllerResponse() {
    }
    public GetAllVisiterControllerResponse(RestHeader header) {
        super(header);
    }

    public GetAllVisiterControllerResponse(RestHeader header, List<GetVisiterDTO> getVisiterDTOList) {
        super(header);
        this.getVisiterDTOList = getVisiterDTOList;
    }

    public List<GetVisiterDTO> getGetVisiterDTOList() {
        return getVisiterDTOList;
    }

    public void setGetVisiterDTOList(List<GetVisiterDTO> getVisiterDTOList) {
        this.getVisiterDTOList = getVisiterDTOList;
    }
}
