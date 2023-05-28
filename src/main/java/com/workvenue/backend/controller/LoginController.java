package com.workvenue.backend.controller;

import com.workvenue.backend.core.constant.SuccessMessage;
import com.workvenue.backend.core.util.MessageUtil;
import com.workvenue.backend.core.util.RestHeader;
import com.workvenue.backend.core.util.exception.ControllerException;
import com.workvenue.backend.data.request.login.LoginControllerRequest;
import com.workvenue.backend.data.response.login.LoginControllerResponse;
import com.workvenue.backend.service.impl.LoginServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Login")
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginServiceImpl loginService;


    @PostMapping
    @ApiOperation(value = "Generate token")
    public ResponseEntity<LoginControllerResponse> createVenue(
            @RequestBody LoginControllerRequest loginControllerRequest) throws ControllerException {
        LoginControllerResponse response = loginService.login(loginControllerRequest);
        response.setHeader(new RestHeader(true, MessageUtil.getMessage("Login", SuccessMessage.AUTHENTICATED), null));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
