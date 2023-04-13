package com.workvenue.backend.data.response;

import com.workvenue.backend.core.util.RestHeader;

import java.io.Serializable;

public class BaseControllerResponse implements Serializable {


    private RestHeader header;

    public BaseControllerResponse() {}

    public BaseControllerResponse(RestHeader header) {
        this.header = header;
    }


    public RestHeader getHeader() {
        return header;
    }

    public void setHeader(RestHeader header) {
        this.header = header;
    }

}
