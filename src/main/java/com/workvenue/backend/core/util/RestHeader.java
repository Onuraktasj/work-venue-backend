package com.workvenue.backend.core.util;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestHeader implements Serializable {

    static final long serialVersionUID = 5719147879887036488L;

    private boolean success;
    private String message;
    private ErrorDetail errorDetail;
}
