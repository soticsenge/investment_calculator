package com.invest.demo.repositories;

import com.invest.demo.entities.PropertyForSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class CustomPropertyForSaleRepositoryImpl implements CustomPropertyForSaleRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Map<String, List<PropertyForSale>> getGroupedPropertiesByCity() {
        return mongoTemplate.findAll(PropertyForSale.class).stream().collect(groupingBy(PropertyForSale::getCity));
    }
}
