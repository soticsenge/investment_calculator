package com.invest.demo.controllers;

import com.invest.demo.entities.PropertyForSale;
import com.invest.demo.repositories.PropertyForSaleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.invest.demo.tasks.PropertiesForSaleLoader;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PropertyForSaleController {

    @Resource
    PropertyForSaleRepository propertyForSaleRepository;

    @Resource
    PropertiesForSaleLoader propertiesForSaleLoader;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/properties/sale", params = "location")
    public List<PropertyForSale> getPropertiesForSale(String location) {
        return propertyForSaleRepository.findAll(Sort.by(Sort.Direction.DESC, "priceRatio"));
    }

    @RequestMapping(value = "/properties/sale/load", method = RequestMethod.POST)
    public String loadPropertiesForSale() {
        propertiesForSaleLoader.start();
        return "OK";
    }

    @RequestMapping(value = "/properties/sale", method = RequestMethod.POST)
    PropertyForSale savePropertiesForSale(@Valid @RequestBody PropertyForSale propertyForSale) {
        return propertyForSaleRepository.save(propertyForSale);
    }
}
