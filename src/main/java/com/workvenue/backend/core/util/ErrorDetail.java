package com.workvenue.backend.core.util;

import java.io.Serializable;
import java.util.Date;

public class ErrorDetail implements Serializable {

    static final long serialVersionUID = 2562135292733411909L;
    private int errorCode;
    private String errorMessage;
    private Date timestamp;

    public ErrorDetail(int errorCode, String errorMessage, Date timestamp) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
