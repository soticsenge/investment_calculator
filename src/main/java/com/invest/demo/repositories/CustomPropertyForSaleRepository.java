package com.invest.demo.repositories;

import com.invest.demo.entities.PropertyForSale;

import java.util.List;
import java.util.Map;

public interface CustomPropertyForSaleRepository {
    public Map<String, List<PropertyForSale>> getGroupedPropertiesByCity();
}
