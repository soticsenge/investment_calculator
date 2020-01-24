package com.invest.demo.entities;

public class InvestmentData {
    private String city;
    private Double ratio;

    public Double getAverageRentPrice() {
        return averageRentPrice;
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

    private Double averageRentPrice;
    private Double averageSalePrice;

    public InvestmentData(String city, Double averageSalePrice, Double averageRentPrice) {
        this.city = city;
        this.averageSalePrice = averageSalePrice;
        this.averageRentPrice = averageRentPrice;
        this.ratio = averageRentPrice/averageSalePrice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
                "city='" + city + '\'' +
                ", ratio=" + ratio +
                ", averageRentPrice=" + averageRentPrice +
                ", averageSalePrice=" + averageSalePrice +
                '}';
    }
}
