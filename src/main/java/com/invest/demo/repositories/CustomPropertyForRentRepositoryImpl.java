package com.invest.demo.repositories;

import com.invest.demo.entities.PropertyForRent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class CustomPropertyForRentRepositoryImpl implements CustomPropertyForRentRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Map<String, List<PropertyForRent>> getGroupedProperties() {
        return mongoTemplate.findAll(PropertyForRent.class).stream().collect(groupingBy(PropertyForRent::getCity));
    }
}
