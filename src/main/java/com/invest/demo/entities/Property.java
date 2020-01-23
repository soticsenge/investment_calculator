package com.invest.demo.entities;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public abstract class Property {

    @Id
    public String id;

    @NotEmpty(message = "Location cannot be empty.")
    public String location;

    @NotEmpty(message = "Size cannot be empty.")
    public String size;

    @NotEmpty(message = "Price cannot be empty.")
    public String price;

    public String balconySize;
    public String link;
    public String roomNr;
    public String street;
    public String city;

    protected Property(String street,
                       String city,
                       String size,
                       String price,
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
}