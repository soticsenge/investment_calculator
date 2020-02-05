package com.invest.demo.entities;

import org.springframework.data.annotation.Id;

public class LocationData {
    @Id
    protected String id;

    protected Object geoJson;

    public String getId() {
        return id;
    }

    public Object getGeoJson() {
        return geoJson;
    }

    public void setGeoJson(Object geoJson) {
        this.geoJson = geoJson;
    }
}
