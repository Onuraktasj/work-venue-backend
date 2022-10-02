package com.workvenue.backend.data.dto.response;


import com.workvenue.backend.data.dto.other.RestHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseControllerResponse {
    private RestHeader header;
}
