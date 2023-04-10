package com.workvenue.backend.exception.custom;

import com.workvenue.backend.core.constant.ControllerName;

public class ControllerException extends Exception {
    private Exception controllerException;
    private String errorMessage;
    private ControllerName controllerName;

    public ControllerException() {
    }

    public ControllerException(Exception controllerException, ControllerName controllerName) {
        this.controllerException = controllerException;
        this.controllerName = controllerName;
        this.errorMessage = controllerException.getMessage();
    }

    public ControllerException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Exception getControllerException() {
        return controllerException;
    }

    public void setControllerException(Exception controllerException) {
        this.controllerException = controllerException;
    }

    @Override
    public String toString() {
        return controllerName.getName() + " Controller Exception: " + errorMessage + "| Error Detail(" + controllerException+")";
    }
}