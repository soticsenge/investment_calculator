package com.invest.demo.entities;

import java.util.Optional;

public class PropertyForSale extends Property {

    public String priceRatio;

    public PropertyForSale(String street, String city, String size, String price, String priceRatio, String balconySize) {
        this.location = Optional.ofNullable(street).map(str -> str + " ,").orElse("") + city;
        this.size = size;
        this.priceRatio = priceRatio;
        this.price = price;
        this.street = street;
        this.city = city;
        if (balconySize == null) {
            this.balconySize = "0";
        } else {
            this.balconySize = balconySize;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, location='%s', size='%s', priceRatio='%s']",
                id, location, size, price, priceRatio);
    }
}
