package com.workvenue.backend.data.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private String errorCode;
    private String errorMessage;
    private Date timestamp;
}
