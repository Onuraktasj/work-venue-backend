package com.workvenue.backend.exception.custom;

public class ControllerException extends Exception{
    private final String message;
    private final String value;

    public ControllerException(String message,String value) {
        this.message=message;
        this.value=value;
    }

    public String getMessage() {
        return message+" "+value+" Controller Exception";
    }
}
