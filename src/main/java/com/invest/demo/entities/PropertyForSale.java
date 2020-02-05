package com.invest.demo.entities;

public class PropertyForSale extends Property {

    public PropertyForSale(String street,
                           String city,
                           Double size,
                           Double price,
                           Double balconySize,
                           String link,
                           String roomNr,
                           Double priceRatio,
                           String imgUrl) {
        super(street, city, size, price, balconySize, link, roomNr, imgUrl);
        this.priceRatio = priceRatio;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, location='%s', size='%s', priceRatio='%s']",
                id, location, size, price, priceRatio);
    }
}
