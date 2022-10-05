package com.workvenue.backend.core.exception;

import com.workvenue.backend.core.constant.RestMessage;
import com.workvenue.backend.core.exception.custom.BusinessControllerException;
import com.workvenue.backend.data.dto.other.ErrorDetail;
import com.workvenue.backend.data.dto.other.RestHeader;
import com.workvenue.backend.data.dto.response.BaseControllerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice(basePackages = "com.workvenue.backend")
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<BaseControllerResponse> handleIllegalStateException(IllegalStateException ex, HttpServletRequest  request) {
        BaseControllerResponse baseControllerResponse=new BaseControllerResponse();
        ErrorDetail errorDetail=new ErrorDetail("404",ex.getMessage(),new Date());
        baseControllerResponse.setHeader(new RestHeader(false, RestMessage.GENERAL_ERROR,errorDetail));
        LOGGER.error("An error occurred: " + errorDetail + " and request details: " + request.toString());
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {BusinessControllerException.class})
    public ResponseEntity<BaseControllerResponse> handlBusinessControllerException(IllegalStateException ex, HttpServletRequest request) {
        BaseControllerResponse baseControllerResponse=new BaseControllerResponse();
        ErrorDetail errorDetail=new ErrorDetail("0",ex.getMessage(),new Date());
        baseControllerResponse.setHeader(new RestHeader(false, RestMessage.GENERAL_ERROR,errorDetail));
        LOGGER.error("An error occurred: " + errorDetail + " and request details: " + request.toString());
        return new ResponseEntity( HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

