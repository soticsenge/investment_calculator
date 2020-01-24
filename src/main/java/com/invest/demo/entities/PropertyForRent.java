package com.invest.demo.entities;

public class PropertyForRent extends Property {

    public PropertyForRent(String street,
                           String city,
                           Double size,
                           Double price,
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

    public Double getPriceRatio() {
        return Double.valueOf(this.price) / Double.valueOf(this.size);
    }
}
