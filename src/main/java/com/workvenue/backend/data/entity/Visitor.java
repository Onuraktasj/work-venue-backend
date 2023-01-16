package com.workvenue.backend.data.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "visitors")
public class Visitor {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @NotNull
    @NotBlank
    @Email(message = "Bu mail adresi zaten kayıtlı.")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
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

    @Length(max = 155)
    @Column(name = "description")
    private String description;

    @Length(max = 200)
    @Column(name = "link")
    private String link;

    @Column(name = "status")
    private Integer status;

    @Column(name = "image")
    private String image;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "updated_date")
    private OffsetDateTime updatedDate;

    @Column(name = "deleted_date")
    private OffsetDateTime deletedDate;

    public Visitor() {}

    public Visitor(VisitorBuilder visitorBuilder) {
        this.email = visitorBuilder.email;
        this.password = visitorBuilder.password;
        this.firstName = visitorBuilder.firstName;
        this.lastName = visitorBuilder.lastName;
        this.description = visitorBuilder.description;
        this.link = visitorBuilder.link;
        this.status = visitorBuilder.status;
        this.image = visitorBuilder.image;
        this.createdDate = visitorBuilder.createdDate;
        this.updatedDate = visitorBuilder.updatedDate;
        this.deletedDate = visitorBuilder.deletedDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public OffsetDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(OffsetDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public OffsetDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(OffsetDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public static class VisitorBuilder {
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String description;
        private String link;
        private Integer status;
        private String image;
        private OffsetDateTime createdDate;
        private OffsetDateTime updatedDate;
        private OffsetDateTime deletedDate;

        public VisitorBuilder email(String email) {
            this.email = email;
            return this;
        }
        public VisitorBuilder password(String password) {
            this.password = password;
            return this;
        }
        public VisitorBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public VisitorBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public VisitorBuilder description(String description) {
            this.description = description;
            return this;
        }
        public VisitorBuilder link(String link) {
            this.link = link;
            return this;
        }
        public VisitorBuilder status(Integer status) {
            this.status = status;
            return this;
        }
        public VisitorBuilder image(String image) {
            this.image = image;
            return this;
        }
        public VisitorBuilder createdDate(OffsetDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }
        public VisitorBuilder updatedDate(OffsetDateTime updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }
        public VisitorBuilder deletedDate(OffsetDateTime deletedDate) {
            this.deletedDate = deletedDate;
            return this;
        }
        // Return finally constructed visitor object
        public Visitor build(){
            Visitor visitor=new Visitor(this);
            validateVisitorObject(visitor);
            return visitor;
        }
        private void validateVisitorObject(Visitor visitor){
            // Do something
        }
    }
}
