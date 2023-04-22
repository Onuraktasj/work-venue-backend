package com.workvenue.backend.core.util.exception;

import com.workvenue.backend.core.constant.ControllerName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

    @Override
    public String toString() {
        return controllerName.getName() + " Controller Exception: " + errorMessage + "| Error Detail(" + controllerException+")";
    }
}