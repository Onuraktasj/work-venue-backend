package com.workvenue.backend.service;

import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.data.request.login.LoginControllerRequest;
import com.workvenue.backend.data.response.login.LoginControllerResponse;

public interface LoginService {

    LoginControllerResponse login(LoginControllerRequest request) throws ControllerException;
}
