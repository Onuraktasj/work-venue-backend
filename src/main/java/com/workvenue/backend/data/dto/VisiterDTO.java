package com.workvenue.backend.data.dto;

public class VisiterDTO {

    private String email;
    private String firstName;
    private String lastName;
    private String description;

    public VisiterDTO() {}

    public VisiterDTO(String email, String firstName, String lastName, String description) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
