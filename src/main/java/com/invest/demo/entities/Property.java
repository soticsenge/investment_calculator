package com.invest.demo.entities;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public abstract class Property {

    @Id
    protected String id;

    @NotEmpty(message = "Location cannot be empty.")
    protected String location;

    @NotNull(message = "Size cannot be empty.")
    protected Double size;

    @NotNull(message = "Price cannot be empty.")
    protected Double price;

    @NotEmpty(message = "Link cannot be empty.")
    protected String link;

    protected String balconySize;
    protected String roomNr;
    protected String street;
    protected String city;

    protected Property(String street,
                       String city,
                       Double size,
                       Double price,
                       String balconySize,
                       String link,
                       String roomNr) {
        this.location = Optional.ofNullable(street).map(str -> str + " ,").orElse("") + city;
        this.size = size;
        this.price = price;
        this.street = street;
        this.link = link;
        this.roomNr = roomNr;
        this.city = city;
        if (balconySize == null) {
            this.balconySize = "0";
        } else {
            this.balconySize = balconySize;
        }
    }

    public abstract Double getPriceRatio();

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public Double getSize() {
        return size;
    }

    public Double getPrice() {
        return price;
    }

    public String getBalconySize() {
        return balconySize;
    }

    public String getLink() {
        return link;
    }

    public String getRoomNr() {
        return roomNr;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setBalconySize(String balconySize) {
        this.balconySize = balconySize;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setRoomNr(String roomNr) {
        this.roomNr = roomNr;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }
}