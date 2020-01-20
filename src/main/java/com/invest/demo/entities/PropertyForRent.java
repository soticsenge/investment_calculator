package com.invest.demo.entities;

public class PropertyForRent extends Property {

    public PropertyForRent() {}

    public PropertyForRent(String location, String size, String price) {
        this.location = location;
        this.size = size;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, location='%s', size='%s']",
                id, location, size, price);
    }
}
