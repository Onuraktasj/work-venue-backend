package com.workvenue.backend.core.exception;

import com.workvenue.backend.core.constant.RestMessage;
import com.workvenue.backend.data.dto.other.ErrorDetail;
import com.workvenue.backend.data.dto.other.RestHeader;
import com.workvenue.backend.data.dto.response.BaseControllerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(value = {IllegalStateException.class})
    public BaseControllerResponse handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        BaseControllerResponse baseControllerResponse=new BaseControllerResponse();
        ErrorDetail errorDetail=new ErrorDetail("404",ex.getMessage(),new Date());
        baseControllerResponse.setHeader(new RestHeader(false, RestMessage.GENERAL_ERROR,errorDetail));
        LOGGER.error("An error occurred: " + errorDetail + " and request details: " + request.toString());
        return baseControllerResponse;
    }
}

