package com.workvenue.backend.core.enums;

public enum ErrorCode {
    ILLEGAL_STATE_EXCEPTION(10500,"Illegal State"),
    CONTROLLER_EXCEPTION(10100,"Controller Exception"),
    ACCESS_DENIED_EXCEPTION(403,"Access Denied Exception");

    private final int value;
    private final String reasonPhrase;
    ErrorCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
