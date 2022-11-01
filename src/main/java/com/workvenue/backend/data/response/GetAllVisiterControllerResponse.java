package com.workvenue.backend.data.response;

import com.workvenue.backend.data.dto.VisiterDTO;
import com.workvenue.backend.data.other.RestHeader;

import java.util.List;

public class GetAllVisiterControllerResponse extends BaseControllerResponse{

    List<VisiterDTO> visiterDTOList;

    public GetAllVisiterControllerResponse() {
    }
    public GetAllVisiterControllerResponse(RestHeader header) {
        super(header);
    }

    public GetAllVisiterControllerResponse(RestHeader header, List<VisiterDTO> visiterDTOList) {
        super(header);
        this.visiterDTOList = visiterDTOList;
    }

    public List<VisiterDTO> getGetVisiterDTOList() {
        return visiterDTOList;
    }

    public void setGetVisiterDTOList(List<VisiterDTO> visiterDTOList) {
        this.visiterDTOList = visiterDTOList;
    }
}
