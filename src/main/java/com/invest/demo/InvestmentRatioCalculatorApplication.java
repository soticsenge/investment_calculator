package com.invest.demo;

import com.invest.demo.repositories.PropertyForRentRepository;
import com.invest.demo.repositories.PropertyForSaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class InvestmentRatioCalculatorApplication  {


    public static final String baseUrlRent = "https://ingatlan.com/lista/kiado+lakas";
    private PropertyForRentRepository propertyForRentRepository;

    public static void main(String[] args) {
        SpringApplication.run(InvestmentRatioCalculatorApplication.class, args);
    }
}
