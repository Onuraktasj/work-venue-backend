package com.workvenue.backend.data.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class RegisterVisiterControllerResponse extends BaseControllerResponse {

    private String firstName;
    private String lastName;
}
