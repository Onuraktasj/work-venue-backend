package com.workvenue.backend.data.other;

public class RestHeader {

    private boolean success;
    private String message;
    private ErrorDetail errorDetail;

    public RestHeader(boolean success, String message, ErrorDetail errorDetail) {
        this.success = success;
        this.message = message;
        this.errorDetail = errorDetail;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorDetail getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(ErrorDetail errorDetail) {
        this.errorDetail = errorDetail;
    }

}
