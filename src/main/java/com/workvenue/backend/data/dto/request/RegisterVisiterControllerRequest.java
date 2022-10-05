package com.workvenue.backend.data.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class RegisterVisiterControllerRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String description;
    private String link;
}
