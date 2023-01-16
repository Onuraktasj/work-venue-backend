package com.workvenue.backend.data.dto;

import com.workvenue.backend.data.enums.Category;
import com.workvenue.backend.data.enums.Network;
import com.workvenue.backend.data.entity.Visitor;
import com.workvenue.backend.data.enums.Status;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class VenueDTO implements Serializable {

    private String name;

    private String address;

    private OffsetDateTime openingTime;

    private OffsetDateTime closingTime;

    private Category category;

    private Network network;

    private Status status;

    public VenueDTO() {
    }

    public VenueDTO(String name, String address, OffsetDateTime openingTime, OffsetDateTime closingTime, Category category, Network network, Status status) {
        this.name = name;
        this.address = address;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.category = category;
        this.network = network;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OffsetDateTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(OffsetDateTime openingTime) {
        this.openingTime = openingTime;
    }

    public OffsetDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(OffsetDateTime closingTime) {
        this.closingTime = closingTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
