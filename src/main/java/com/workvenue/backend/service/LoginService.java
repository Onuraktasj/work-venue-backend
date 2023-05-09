package com.workvenue.backend.service;

import com.workvenue.backend.data.request.login.LoginControllerRequest;
import com.workvenue.backend.data.response.login.LoginControllerResponse;

public interface LoginService {

    LoginControllerResponse login(LoginControllerRequest request);
}
