package com.workvenue.backend.data.dto.other;


import com.workvenue.backend.data.dto.other.ErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestHeader {
    private boolean success;
    private String message;
    private ErrorDetail errorDetail;
}
