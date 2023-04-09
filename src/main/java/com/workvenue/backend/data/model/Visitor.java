package com.workvenue.backend.data.model;

import com.workvenue.backend.data.enums.Status;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "visitors")
public class Visitor {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Email(message = "Bu mail adresi zaten kayıtlı.")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Length(min = 2)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Length(min = 2)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Length(max = 155)
    @Column(name = "description")
    private String description;

    @Length(max = 200)
    @Column(name = "link")
    private String link;

    @Column(name = "status")
    private Status status;

    @Column(name = "image")
    private String image;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "updated_date")
    private OffsetDateTime updatedDate;

    @Column(name = "deleted_date")
    private OffsetDateTime deletedDate;
}
