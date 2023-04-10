package com.workvenue.backend.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Size(min = 6, max = 25, message = "6-25 karakter arasında olmalıdır.")
    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    //TODO: password custom annotation, regex.
    @Size(min = 8, max = 50, message = "8-50 karakter arasında olmalıdır.")
    @Column(name = "password")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<AppUserRole> roles;
}
