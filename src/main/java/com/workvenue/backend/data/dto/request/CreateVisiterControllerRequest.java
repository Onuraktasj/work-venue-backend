package com.workvenue.backend.data.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateVisiterControllerRequest {
    private UUID id;
    private String email;
    private String password;
    private String firstname;
    private String lastName;
    private String description;
    private String link;
    private String image;

}
