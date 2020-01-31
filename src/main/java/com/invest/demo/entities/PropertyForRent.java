package com.invest.demo.entities;

public class PropertyForRent extends Property {

    public PropertyForRent(String street,
                           String city,
                           Double size,
                           Double price,
                           Double balconySize,
                           String link,
                           String roomNr) {
        super(street, city, size, price, balconySize, link, roomNr);
        this.priceRatio = price/(size + (balconySize/2));
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, location='%s', size='%s']",
                id, location, size, price);
    }
}
