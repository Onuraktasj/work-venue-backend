package com.workvenue.backend.data.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Size(min = 6, max = 25, message = "6-25 karakter arasında olmalıdır.") // unique = true, nullable = false
    private String username;

    //TODO: password custom annotation, regex.
//    @Size(min = 8, max = 50, message = "8-50 karakter arasında olmalıdır.") //frontendde olmalı encrypt data geliyor
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles;
}
