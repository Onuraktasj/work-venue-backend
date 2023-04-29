package com.workvenue.backend.data.model;

import com.workvenue.backend.core.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "visitors")
@NoArgsConstructor
@Accessors(chain = true)
public class Visitor extends BaseUser implements Serializable {

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

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "image")
    private String image;

    @Column(name = "created_date")
    private OffsetDateTime createdDate; //TODO: ortak yap ve util metodda oluşturup at, kod tekrar yapma.

    @Column(name = "updated_date")
    private OffsetDateTime updatedDate;

    @Column(name = "deleted_date")
    private OffsetDateTime deletedDate;
}
