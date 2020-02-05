package com.invest.demo.repositories;

import com.invest.demo.entities.PropertyForRent;
import com.invest.demo.entities.PropertyForSale;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PropertyForSaleRepository extends MongoRepository<PropertyForSale, String>, CustomPropertyForSaleRepository {
    public abstract Optional<PropertyForSale> findById(String id);
    public abstract Optional<PropertyForSale> findByCity(String city);
}