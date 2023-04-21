package com.workvenue.backend.core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestHeader {

    private boolean success;
    private String message;
    private ErrorDetail errorDetail;
}
