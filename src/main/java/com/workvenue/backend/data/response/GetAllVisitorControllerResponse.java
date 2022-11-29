package com.workvenue.backend.data.response;

import com.workvenue.backend.data.dto.VisitorDTO;
import com.workvenue.backend.data.other.RestHeader;

import java.util.Set;

public class GetAllVisitorControllerResponse extends BaseControllerResponse{

    Set<VisitorDTO> visitorDTOSet;

    public GetAllVisitorControllerResponse() {
    }
    public GetAllVisitorControllerResponse(RestHeader header) {
        super(header);
    }

    public GetAllVisitorControllerResponse(RestHeader header, Set<VisitorDTO> visitorDTOSet) {
        super(header);
        this.visitorDTOSet = visitorDTOSet;
    }

    public Set<VisitorDTO> getGetVisitorDTOSet() {
        return visitorDTOSet;
    }

    public void setGetVisitorDTOSet(Set<VisitorDTO> visitorDTOSet) {
        this.visitorDTOSet = visitorDTOSet;
    }
}
