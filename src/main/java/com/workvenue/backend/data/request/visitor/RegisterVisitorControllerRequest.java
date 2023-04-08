package com.workvenue.backend.data.request.visitor;

import com.workvenue.backend.data.dto.VisitorDTO;
import com.workvenue.backend.data.request.BaseControllerRequest;

public class RegisterVisitorControllerRequest extends BaseControllerRequest {

    private VisitorDTO visitorDTO;

    public RegisterVisitorControllerRequest(){
    }

    public RegisterVisitorControllerRequest(VisitorDTO visitorDTO) {
        this.visitorDTO = visitorDTO;
    }

    public VisitorDTO getVisitorDTO() {
        return visitorDTO;
    }

    public void setVisitorDTO(VisitorDTO visitorDTO) {
        this.visitorDTO = visitorDTO;
    }
}
