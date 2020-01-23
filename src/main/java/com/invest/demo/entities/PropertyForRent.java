package com.invest.demo.entities;

import java.util.Optional;

public class PropertyForRent extends Property {

    public PropertyForRent(String street,
                           String city,
                           String size,
                           String price,
                           String balconySize,
                           String link,
                           String roomNr) {
        super(street, city, size, price, balconySize, link, roomNr);
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, location='%s', size='%s']",
                id, location, size, price);
    }
}
