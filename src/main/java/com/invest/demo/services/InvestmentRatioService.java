package com.invest.demo.services;

import com.invest.demo.entities.InvestmentData;
import com.invest.demo.entities.Property;
import com.invest.demo.entities.PropertyForRent;
import com.invest.demo.entities.PropertyForSale;
import com.invest.demo.repositories.PropertyForRentRepository;
import com.invest.demo.repositories.PropertyForSaleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InvestmentRatioService {

    @Resource
    PropertyForRentRepository propertyForRentRepository;

    @Resource
    PropertyForSaleRepository propertyForSaleRepository;

    public List<InvestmentData> getInvestmentRatios() {
        Map<String, List<PropertyForRent>> groupedPropertiesForRent = propertyForRentRepository.getGroupedProperties();
        return propertyForSaleRepository.getGroupedProperties()
                .entrySet()
                .stream()
                .map(entry ->
                        new InvestmentData(
                                entry.getKey(),
                                getAveragePriceRatio(entry.getValue()),
                                getAveragePriceRatio(groupedPropertiesForRent.get(entry.getKey()))))
                .sorted(Comparator.comparing(InvestmentData::getRatio))
                .collect(Collectors.toList());

    }

    private <P extends Property> double  getAveragePriceRatio(List<P> properties) {
        return properties
                .stream()
                .mapToDouble(property -> property.getPriceRatio())
                .average().orElse(0.0);
    }
}
