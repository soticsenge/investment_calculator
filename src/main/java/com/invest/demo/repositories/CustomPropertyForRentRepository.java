package com.invest.demo.repositories;

import com.invest.demo.entities.PropertyForRent;
import com.invest.demo.entities.PropertyForSale;

import java.util.List;
import java.util.Map;

public interface CustomPropertyForRentRepository {
    public Map<String, List<PropertyForRent>> getGroupedPropertiesByCity();
}
