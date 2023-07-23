package com.workvenue.backend.core.util.exception;

import com.workvenue.backend.core.enums.ControllerName;
import lombok.*;

@Getter
@Setter
@ToString
public class ControllerException extends Exception {
    private Exception controllerException;
    private String errorMessage;
    private ControllerName controllerName;

    public ControllerException(Exception controllerException, ControllerName controllerName) {
        this.controllerException = controllerException;
        this.controllerName = controllerName;
        this.errorMessage = controllerException.getMessage();
    }

    public ControllerException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}