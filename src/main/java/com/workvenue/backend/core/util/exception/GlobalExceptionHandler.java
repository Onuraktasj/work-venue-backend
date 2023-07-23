package com.workvenue.backend.core.util.exception;

import com.workvenue.backend.core.enums.ErrorCode;
import com.workvenue.backend.core.constant.ErrorMessage;
import com.workvenue.backend.core.util.ErrorDetail;
import com.workvenue.backend.core.util.RestHeader;
import com.workvenue.backend.data.response.BaseControllerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * The UI does not show the error message if the success status is false, but shows it if it is true.
 */

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, NullPointerException.class,
            RuntimeException.class, HttpServerErrorException.InternalServerError.class, Exception.class})
    public ResponseEntity<BaseControllerResponse> handleIllegalStateException(IllegalStateException ex,
                                                                              WebRequest request) {
        BaseControllerResponse baseControllerResponse = new BaseControllerResponse();
        ErrorDetail errorDetail = new ErrorDetail(ErrorCode.ILLEGAL_STATE_EXCEPTION.getValue(),
                ErrorCode.ILLEGAL_STATE_EXCEPTION.getReasonPhrase() + " " + ex.getMessage(), new Date());
        baseControllerResponse.setHeader(
                new RestHeader(false, ErrorMessage.GeneralError.UNEXPECTED_ERROR, errorDetail));
        return new ResponseEntity<>(baseControllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ControllerException.class})
    public ResponseEntity<BaseControllerResponse> handleControllerException(ControllerException ex,
                                                                            WebRequest request) {
        BaseControllerResponse baseControllerResponse = new BaseControllerResponse();
        ErrorDetail errorDetail = new ErrorDetail(ErrorCode.CONTROLLER_EXCEPTION.getValue(),
                ErrorCode.CONTROLLER_EXCEPTION.getReasonPhrase() + " " + ex.toString(), new Date());
        baseControllerResponse.setHeader(new RestHeader(true, ex.getErrorMessage(), errorDetail));
        return new ResponseEntity<>(baseControllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}