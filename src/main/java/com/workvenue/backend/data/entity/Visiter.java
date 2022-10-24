package com.workvenue.backend.data.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "visiters")
@PrimaryKeyJoinColumn(name = "user_id")
public class Visiter extends User{

    @Length(max = 155)
    private String description;

    @Length(max = 200)
    private String link;

    private Boolean isActive;

    private String image;

    public Visiter() {
    }

    public Visiter(String description, String link, Boolean isActive, String image) {
        this.description = description;
        this.link = link;
        this.isActive = isActive;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
