package com.invest.demo;

import com.invest.demo.entities.Property;
import com.invest.demo.entities.PropertyForRent;
import com.invest.demo.repositories.PropertyForRentRepository;
import com.invest.demo.repositories.PropertyForSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class InvestmentRatioCalculatorApplication implements CommandLineRunner {

    @Resource
    private PropertyForSaleRepository propertyForSaleRepository;
    @Resource
    private PropertyForRentRepository propertyForRentRepository;

    public static void main(String[] args) {
        SpringApplication.run(InvestmentRatioCalculatorApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        /*propertyForRentRepository.findAll().forEach(property -> {
            trasformData(property, " Ft/hó");
            propertyForRentRepository.save(property);
        });
        propertyForSaleRepository.findAll().forEach(property -> {
            trasformData(property, " M Ft");
            property.setPriceRatio(property.getPriceRatio().replace(" Ft/m","").replaceAll("\\s",""));
            propertyForSaleRepository.save(property);
        });*/
    }

    /*private void trasformData (Property property, String postfix) {
        property.setPrice(property.getPrice().replace(postfix,"").replaceAll("\\s",""));
        property.setSize(property.getSize().replaceAll("m.*","").replaceAll("\\s",""));
        if(property.getBalconySize() != null) {
            property.setBalconySize(property.getBalconySize().replaceAll("m.*","").replaceAll("\\s",""));
        } else property.setBalconySize("0");

        String[] tmp = property.getLocation().split(", ");
        property.setStreet(tmp[0]);
        property.setCity(tmp[1]);
    }*/

}
