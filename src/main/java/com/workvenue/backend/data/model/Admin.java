package com.workvenue.backend.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "admins")
@NoArgsConstructor
@Accessors(chain = true)
public class Admin extends AppUser {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
}
