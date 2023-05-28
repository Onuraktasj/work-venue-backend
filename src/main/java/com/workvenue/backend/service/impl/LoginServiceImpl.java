package com.workvenue.backend.service.impl;

import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.security.JWTTokenProvider;
import com.workvenue.backend.data.request.login.LoginControllerRequest;
import com.workvenue.backend.data.response.login.LoginControllerResponse;
import com.workvenue.backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JWTTokenProvider jwtTokenProvider;

    @Override
    public LoginControllerResponse login(LoginControllerRequest request) throws ControllerException {
        LoginControllerResponse response = new LoginControllerResponse();
        Authentication authentication = jwtTokenProvider.authenticate(request);
        if (authentication.isAuthenticated()) {
            response.setAccessToken(jwtTokenProvider.generateAccessToken(authentication));
        } else {
            throw new ControllerException("Authentication başarısız.");
        }
        return response;
    }
}