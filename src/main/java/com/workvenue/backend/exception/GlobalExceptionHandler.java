package com.workvenue.backend.exception;

import com.workvenue.backend.core.constant.ErrorCode;
import com.workvenue.backend.core.constant.RestMessage;
import com.workvenue.backend.data.dto.other.ErrorDetail;
import com.workvenue.backend.data.dto.other.RestHeader;
import com.workvenue.backend.data.dto.response.BaseControllerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<BaseControllerResponse> handleIllegalStateException(IllegalStateException ex, HttpServletRequest request) {
        BaseControllerResponse baseControllerResponse = new BaseControllerResponse();
        ErrorDetail errorDetail = new ErrorDetail(ErrorCode.ILLEGAL_STATE_EXCEPTION.getValue(), ErrorCode.ILLEGAL_STATE_EXCEPTION+" "+ex.getMessage(), new Date());
        baseControllerResponse.setHeader(new RestHeader(false, RestMessage.GENERAL_ERROR, errorDetail));
        LOGGER.error("An error occurred: " + errorDetail + " and request details: " + request.toString());
        return new ResponseEntity(baseControllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<BaseControllerResponse> handleControllerException(Exception ex, HttpServletRequest request) {
        BaseControllerResponse baseControllerResponse = new BaseControllerResponse();
        ErrorDetail errorDetail = new ErrorDetail(ErrorCode.GENERAL_EXCEPTION.getValue(), ErrorCode.GENERAL_EXCEPTION+" "+ex.getMessage(), new Date());
        baseControllerResponse.setHeader(new RestHeader(false, RestMessage.GENERAL_ERROR, errorDetail));
        LOGGER.error("An error occurred: " + errorDetail + " and request details: " + request.toString());
        return new ResponseEntity(baseControllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

