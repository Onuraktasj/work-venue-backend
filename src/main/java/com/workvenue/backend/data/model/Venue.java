package com.workvenue.backend.data.model;

import com.workvenue.backend.core.enums.Category;
import com.workvenue.backend.core.enums.Network;
import com.workvenue.backend.core.enums.Status;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "venues")
@NoArgsConstructor
@Accessors(chain = true)
public class Venue {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "opening_time")
    private String openingTime;

    @Column(name = "closing_time")
    private String closingTime;

    @Column(name = "category")
    private Category category;

    @Column(name = "network")
    private Network network;

    @Column(name = "status")
    private Status status;
}