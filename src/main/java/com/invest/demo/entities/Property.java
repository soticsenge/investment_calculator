package com.invest.demo.entities;

import org.springframework.data.annotation.Id;

public abstract class Property {

    @Id
    public String id;

    public String location;
    public String size;
    public String price;
    public String balconySize;
    public String link;
    public String roomNr;
    public String street;
    public String city;
}