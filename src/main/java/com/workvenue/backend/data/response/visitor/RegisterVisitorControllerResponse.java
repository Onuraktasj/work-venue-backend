package com.workvenue.backend.data.response.visitor;

import com.workvenue.backend.data.dto.VisitorDTO;
import com.workvenue.backend.core.util.RestHeader;
import com.workvenue.backend.data.response.BaseControllerResponse;

public class RegisterVisitorControllerResponse extends BaseControllerResponse {
    //TODO: refactor
    public RegisterVisitorControllerResponse() {
    }

    public RegisterVisitorControllerResponse(RestHeader header, VisitorDTO visitorDTO) {
        super(header);
    }
}
