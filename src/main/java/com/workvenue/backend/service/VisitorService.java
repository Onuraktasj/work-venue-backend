package com.workvenue.backend.service;

import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.data.request.visitor.RegisterVisitorControllerRequest;
import com.workvenue.backend.data.request.visitor.UpdateVisitorControllerRequest;
import com.workvenue.backend.data.response.visitor.GetAllVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.RegisterVisitorControllerResponse;
import com.workvenue.backend.data.response.visitor.UpdateVisitorControllerResponse;

public interface VisitorService {

    RegisterVisitorControllerResponse register(RegisterVisitorControllerRequest request) throws ControllerException;

    UpdateVisitorControllerResponse update(UpdateVisitorControllerRequest request) throws ControllerException;

    GetAllVisitorControllerResponse findAll() throws ControllerException;
}
