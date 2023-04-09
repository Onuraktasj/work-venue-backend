package com.workvenue.backend.data.request;

import com.workvenue.backend.exception.util.RestHeader;

public class BaseControllerRequest {


    private RestHeader header;

    public BaseControllerRequest() {}

    public BaseControllerRequest(RestHeader header) {
        this.header = header;
    }


    public RestHeader getHeader() {
        return header;
    }

    public void setHeader(RestHeader header) {
        this.header = header;
    }
}
