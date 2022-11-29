package com.workvenue.backend.data.request;

import com.workvenue.backend.data.dto.VisitorDTO;

public class RegisterVisitorControllerRequest {

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
