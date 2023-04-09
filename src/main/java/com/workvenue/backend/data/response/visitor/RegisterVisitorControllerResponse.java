package com.workvenue.backend.data.response.visitor;

import com.workvenue.backend.data.dto.VisitorDTO;
import com.workvenue.backend.exception.util.RestHeader;
import com.workvenue.backend.data.response.BaseControllerResponse;

public class RegisterVisitorControllerResponse extends BaseControllerResponse {

    private VisitorDTO visitorDTO;

    public RegisterVisitorControllerResponse() {
    }

    public RegisterVisitorControllerResponse(RestHeader header, VisitorDTO visitorDTO) {
        super(header);
        this.visitorDTO = visitorDTO;
    }

    public VisitorDTO getVisitorDTO() {
        return visitorDTO;
    }

    public void setVisitorDTO(VisitorDTO visitorDTO) {
        this.visitorDTO = visitorDTO;
    }
}
