package com.workvenue.backend.data.entity;

import com.workvenue.backend.data.enums.Category;
import com.workvenue.backend.data.enums.Network;
import com.workvenue.backend.data.enums.Status;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "venues")
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

    public Venue() {
    }

    public Venue(String name, String address, String openingTime, String closingTime, Category category, Network network, Status status) {
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

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue venue = (Venue) o;
        return id.equals(venue.id) && Objects.equals(name, venue.name) && Objects.equals(address, venue.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }
}
