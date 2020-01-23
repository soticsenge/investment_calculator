package com.invest.demo.controllers;

import com.invest.demo.entities.PropertyForRent;
import com.invest.demo.entities.PropertyForRent;
import com.invest.demo.repositories.PropertyForRentRepository;
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
public class PropertyForRentController {

    @Resource
    PropertyForRentRepository propertyForRentRepository;

    @RequestMapping("/properties/rent")
    public List<PropertyForRent> getPropertiesForRent() {
        Pageable limit = PageRequest.of(0,30);
        return propertyForRentRepository.findAll(limit).toList();
    }

    @RequestMapping(value = "/properties/rent", method = RequestMethod.POST)
    PropertyForRent savePropertiesForRent(@Valid @RequestBody PropertyForRent propertyForRent) {
        return propertyForRentRepository.save(propertyForRent);
    }
}
