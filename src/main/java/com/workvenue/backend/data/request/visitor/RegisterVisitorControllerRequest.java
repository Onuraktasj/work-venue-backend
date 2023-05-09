package com.workvenue.backend.data.request.visitor;

import com.workvenue.backend.data.dto.VisitorDTO;
import com.workvenue.backend.data.request.BaseControllerRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RegisterVisitorControllerRequest extends BaseControllerRequest {

    private VisitorDTO visitorDTO;
}
