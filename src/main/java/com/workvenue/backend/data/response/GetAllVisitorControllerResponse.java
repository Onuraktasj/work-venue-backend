package com.workvenue.backend.data.response;

import com.workvenue.backend.data.dto.VisitorDTO;
import com.workvenue.backend.data.other.RestHeader;

import java.util.List;

public class GetAllVisitorControllerResponse extends BaseControllerResponse{

    List<VisitorDTO> visitorDTOList;

    public GetAllVisitorControllerResponse() {
    }
    public GetAllVisitorControllerResponse(RestHeader header) {
        super(header);
    }

    public GetAllVisitorControllerResponse(RestHeader header, List<VisitorDTO> visitorDTOList) {
        super(header);
        this.visitorDTOList = visitorDTOList;
    }

    public List<VisitorDTO> getGetVisitorDTOList() {
        return visitorDTOList;
    }

    public void setGetVisitorDTOList(List<VisitorDTO> visitorDTOList) {
        this.visitorDTOList = visitorDTOList;
    }
}
