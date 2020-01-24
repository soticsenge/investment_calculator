package com.invest.demo.controllers;

import com.invest.demo.entities.PropertyForSale;
import com.invest.demo.repositories.PropertyForSaleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PropertyForSaleController {

    @Resource
    PropertyForSaleRepository propertyForSaleRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/properties/sale")
    public List<PropertyForSale> getPropertiesForSale() {
        Pageable limit = PageRequest.of(0,30);
        return propertyForSaleRepository.findAll(limit).toList();
    }

    @RequestMapping(value = "/properties/sale", method = RequestMethod.POST)
    PropertyForSale savePropertiesForSale(@Valid @RequestBody PropertyForSale propertyForSale) {
        return propertyForSaleRepository.save(propertyForSale);
    }
}
