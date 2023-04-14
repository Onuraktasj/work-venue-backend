package com.workvenue.backend.service;

import com.workvenue.backend.core.util.exception.custom.ControllerException;
import com.workvenue.backend.data.request.visitor.RegisterVisitorControllerRequest;
import com.workvenue.backend.data.request.visitor.UpdateVisitorControllerRequest;
import com.workvenue.backend.data.response.visitor.GetAllVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.RegisterVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.UpdateVisitorControllerResponse;

public interface VisitorService {
    GetAllVisitorControllerResponse getAllVisitors() throws ControllerException;
    RegisterVisitorControllerResponse registerVisitor(RegisterVisitorControllerRequest request) throws ControllerException;
    UpdateVisitorControllerResponse updateVisitor(UpdateVisitorControllerRequest request) throws ControllerException;
}
