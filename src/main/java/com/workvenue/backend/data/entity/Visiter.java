package com.workvenue.backend.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "visiters")
@PrimaryKeyJoinColumn(name = "user_id")
@EqualsAndHashCode(callSuper=false)
public class Visiter extends User{

    @Length(max = 155)
    private String description;

    @Length(max = 200)
    private String link;

    private Boolean isActive;

    private String image;
}
