package com.workvenue.backend.data.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateVisiterControllerResponse extends BaseControllerResponse {
    private UUID userId;

    private String description;

    private String link;

    private String image;

    private Boolean isActive;
}
