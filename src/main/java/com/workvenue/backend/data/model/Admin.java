package com.workvenue.backend.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "admins")
@Accessors(chain = true)
public class Admin extends BaseUser implements Serializable {

}
