package com.workvenue.backend.core.exception.custom;

public class BusinessControllerException extends Exception{
    private final String name;

    public BusinessControllerException(String name) {
        this.name = name;
    }

    public String getMessage() {
        return "Visiter email'" + name + "' already exists";
    }
}
