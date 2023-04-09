package com.workvenue.backend.service;

import com.workvenue.backend.data.request.visitor.RegisterVisitorControllerRequest;
import com.workvenue.backend.data.request.visitor.UpdateVisitorControllerRequest;
import com.workvenue.backend.data.response.visitor.GetAllVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.RegisterVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.UpdateVisitorControllerResponse;

public interface VisitorService {
    GetAllVisitorControllerResponse getAllVisitors() throws Exception;

    RegisterVisitorControllerResponse registerVisitor(RegisterVisitorControllerRequest request) throws Exception;
    UpdateVisitorControllerResponse updateVisitor(UpdateVisitorControllerRequest request) throws Exception;
}
