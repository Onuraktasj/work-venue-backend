package com.workvenue.backend.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 6, max = 25, message = "6-25 karakter arasında olmalıdır.") // unique = true, nullable = false
    private String username;

    //TODO: password custom annotation, regex.
    @Size(min = 8, max = 50, message = "8-50 karakter arasında olmalıdır.")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles;
}
