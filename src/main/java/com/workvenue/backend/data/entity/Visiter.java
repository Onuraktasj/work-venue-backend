package com.workvenue.backend.data.entity;



import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "visiters")
@PrimaryKeyJoinColumn(name = "user_id")
@EqualsAndHashCode(callSuper=false)
public class Visiter extends User{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Length(max = 155)
    private String description;

    @Length(max = 200)
    private String link;

    private Boolean isActive;

    private String image;


}
