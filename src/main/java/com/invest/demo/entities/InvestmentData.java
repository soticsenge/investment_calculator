package com.invest.demo.entities;

public class InvestmentData {

    private String locationString;

    private Double ratio;
    private Double averageRentPrice;
    private Double averageSalePrice;
    public Double getAverageRentPrice() {
        return averageRentPrice;
    }

    public InvestmentData(Double averageSalePrice, Double averageRentPrice, String locationString) {
        this.locationString = locationString;
        this.averageSalePrice = averageSalePrice;
        this.averageRentPrice = averageRentPrice;
        this.ratio = averageRentPrice/averageSalePrice;
    }

    public void setAverageRentPrice(Double averageRentPrice) {
        this.averageRentPrice = averageRentPrice;
    }

    public Double getAverageSalePrice() {
        return averageSalePrice;
    }

    public void setAverageSalePrice(Double averageSalePrice) {
        this.averageSalePrice = averageSalePrice;
    }

    public String getLocationString() {
        return locationString;
    }

    public void setLocationString(String locationString) {
        this.locationString = locationString;
    }


    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "InvestmentData{" +
                "locationString='" + locationString + '\'' +
                ", ratio=" + ratio +
                ", averageRentPrice=" + averageRentPrice +
                ", averageSalePrice=" + averageSalePrice +
                '}';
    }
}
