package com.workvenue.backend.data.request;

import com.workvenue.backend.data.dto.VisitorDTO;

public class UpdateVisitorControllerRequest implements BaseControllerRequest{

    private VisitorDTO visitorDTO;

    public UpdateVisitorControllerRequest(){
    }

    public UpdateVisitorControllerRequest(VisitorDTO visitorDTO) {
        this.visitorDTO = visitorDTO;
    }

    public VisitorDTO getVisitorDTO() {
        return visitorDTO;
    }

    public void setVisitorDTO(VisitorDTO visitorDTO) {
        this.visitorDTO = visitorDTO;
    }
}
