package com.invest.demo.repositories;

import com.invest.demo.entities.LocationData;
import com.invest.demo.entities.PropertyForSale;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LocationDataRepository extends MongoRepository<LocationData, String>, CustomPropertyForSaleRepository {
}