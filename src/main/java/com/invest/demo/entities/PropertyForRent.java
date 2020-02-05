package com.invest.demo.entities;

public class PropertyForRent extends Property {

    public PropertyForRent(String street,
                           String city,
                           Double size,
                           Double price,
                           Double balconySize,
                           String link,
                           String roomNr,
                           String imgUrl) {
        super(street, city, size, price, balconySize, link, roomNr, imgUrl);
        this.priceRatio = price/(size + (balconySize/2));
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, location='%s', size='%s']",
                id, location, size, price);
    }
}
