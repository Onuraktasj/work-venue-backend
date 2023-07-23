package com.workvenue.backend.data.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class VisitorDTO implements Serializable {

    static final long serialVersionUID = -5027714984030359297L;

    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String description;
    private String link;
}
