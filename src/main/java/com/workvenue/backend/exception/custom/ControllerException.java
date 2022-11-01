package com.workvenue.backend.exception.custom;

public class ControllerException extends Exception {
    private String errorMessage;

    public ControllerException() {
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

    @Override
    public String toString() {
        return errorMessage + " Controller Exception";
    }
}
