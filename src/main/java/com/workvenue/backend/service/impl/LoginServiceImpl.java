package com.workvenue.backend.service.impl;

import com.workvenue.backend.core.util.security.JwtTokenProvider;
import com.workvenue.backend.data.request.login.LoginControllerRequest;
import com.workvenue.backend.data.response.login.LoginControllerResponse;
import com.workvenue.backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public LoginControllerResponse login(LoginControllerRequest request) {
        // Bu kullanıcı var mı? varsa token oluştur.

        // Kimlik doğrulama için bir authentication token oluşturun
        LoginControllerResponse response = new LoginControllerResponse();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Authentication token başarılıysa JWT token oluşturun ve yanıtla
        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        response.setAccessToken(accessToken);
        return response;
    }



}
