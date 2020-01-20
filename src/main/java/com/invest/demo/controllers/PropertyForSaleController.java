package com.invest.demo.controllers;

import com.invest.demo.entities.PropertyForSale;
import com.invest.demo.repositories.PropertyForSaleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
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
}
