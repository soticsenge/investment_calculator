package com.invest.demo.repositories;


import com.invest.demo.entities.PropertyForRent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PropertyForRentRepository extends MongoRepository<PropertyForRent, String> {

    public Optional<PropertyForRent> findById(String id);
}