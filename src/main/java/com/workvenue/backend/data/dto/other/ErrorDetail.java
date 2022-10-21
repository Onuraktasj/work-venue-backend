package com.workvenue.backend.data.dto.other;

import com.workvenue.backend.core.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private int errorCode;
    private String errorMessage;
    private Date timestamp;
}
