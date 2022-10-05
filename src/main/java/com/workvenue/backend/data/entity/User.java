package com.workvenue.backend.data.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @NotNull
    @NotBlank
//    @Email(message = "Bu mail adresi kayıtlı.")
    @Column(name = "email", unique = true)
    private String email;

    private String password;

    @NotNull
    @NotBlank
    @Length(min = 2)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotBlank
    @Length(min = 2)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

}
