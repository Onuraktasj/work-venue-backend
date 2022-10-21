package com.workvenue.backend.core.constant;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    ILLEGAL_STATE_EXCEPTION(10500,"Illegal State"),
    GENERAL_EXCEPTION(10100,"General Exception")
    ;

    private final int value;
    private final String reasonPhrase;
    ErrorCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
    public int getValue() {
        return value;
    }
}
