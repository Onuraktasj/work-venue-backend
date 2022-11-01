package com.workvenue.backend.exception;

import com.workvenue.backend.core.constant.ErrorCode;
import com.workvenue.backend.core.constant.ErrorMessage;
import com.workvenue.backend.data.other.ErrorDetail;
import com.workvenue.backend.data.other.RestHeader;
import com.workvenue.backend.data.response.BaseControllerResponse;
import com.workvenue.backend.exception.custom.ControllerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, NullPointerException.class, HttpServerErrorException.InternalServerError.class, Exception.class})
    public ResponseEntity<BaseControllerResponse> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        BaseControllerResponse baseControllerResponse = new BaseControllerResponse();
        ErrorDetail errorDetail = new ErrorDetail(ErrorCode.GENERAL_EXCEPTION.getValue(), ErrorCode.GENERAL_EXCEPTION + " " + ex.getMessage(), new Date());
        baseControllerResponse.setHeader(new RestHeader(false, ErrorMessage.GENERAL_ERROR, errorDetail));
        LOGGER.error("An error occurred: " + errorDetail + " and request details: " + request.toString());
        return new ResponseEntity<>(baseControllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ControllerException.class})
    public ResponseEntity<BaseControllerResponse> handleControllerException(ControllerException ex, WebRequest request) {
        BaseControllerResponse baseControllerResponse = new BaseControllerResponse();
        ErrorDetail errorDetail = new ErrorDetail(ErrorCode.ILLEGAL_STATE_EXCEPTION.getValue(), ErrorCode.ILLEGAL_STATE_EXCEPTION + " " + ex.getMessage(), new Date());
        baseControllerResponse.setHeader(new RestHeader(false, ex.toString(), errorDetail));
        LOGGER.error("An error occurred: " + errorDetail + " and request details: " + request.toString());
        return new ResponseEntity<>(baseControllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}