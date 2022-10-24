package com.workvenue.backend.data.response;

import com.workvenue.backend.data.other.RestHeader;

public class RegisterVisiterControllerResponse extends BaseControllerResponse {

    private String firstName;
    private String lastName;

    public RegisterVisiterControllerResponse() {
    }

    public RegisterVisiterControllerResponse(RestHeader header, String firstName, String lastName) {
        super(header);
        this.firstName = firstName;
        this.lastName = lastName;
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
}
