package com.invest.demo.entities;

import java.util.Optional;

public class PropertyForSale extends Property {

    public String priceRatio;

    public PropertyForSale(String street,
                           String city,
                           String size,
                           String price,
                           String balconySize,
                           String link,
                           String roomNr,
                           String priceRatio) {
        super(street, city, size, price, balconySize, link, roomNr);
        this.priceRatio = priceRatio;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, location='%s', size='%s', priceRatio='%s']",
                id, location, size, price, priceRatio);
    }
}
