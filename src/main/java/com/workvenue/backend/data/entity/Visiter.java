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
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            })
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;


    @Length(max = 155)
    private String description;

    @Length(max = 200)
    private String link;

    private Boolean isActive;

    //Image için bir örnek buldum şimdilik böyle kalsın tartışalım onu
    private String image;

    //@OneToMany(mappedBy="visiter", cascade= CascadeType.REMOVE)
    //private List<Post> posts;
    //ManytoOne ile userı maplicaz Post entitiyde

}
