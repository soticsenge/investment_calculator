package com.invest.demo.services;

import com.invest.demo.entities.InvestmentData;
import com.invest.demo.entities.Property;
import com.invest.demo.entities.PropertyForRent;
import com.invest.demo.repositories.PropertyForRentRepository;
import com.invest.demo.repositories.PropertyForSaleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

@Service
public class InvestmentRatioService {

    private Map<String, String> cityName_osmID = Map.ofEntries(
            entry("I. kerület", "221984"),
            entry("II. kerület", "221980"),
            entry("III. kerület", "221976"),
            entry("IV. kerület", "367963"),
            entry("V. kerület", "1606103"),
            entry("VI. kerület", "1606101"),
            entry("VII. kerület", "1606102"),
            entry("VIII. kerület", "1606100"),
            entry("IX. kerület", "1552462"),
            entry("X. kerület", "1552463"),
            entry("XI. kerület", "221998"),
            entry("XII. kerület", "221995"),
            entry("XIII. kerület", "1605916"),
            entry("XIV. kerület", "1606043"),
            entry("XV. kerület", "1606009"),
            entry("XVI. kerület", "1552464"),
            entry("XVII. kerület", "1550599"),
            entry("XVIII. kerület", "1550598"),
            entry("XIX. kerület", "1551290"),
            entry("XX. kerület", "1551291"),
            entry("XXI. kerület", "215618"),
            entry("XXII. kerület", "215621"),
            entry("XXIII. kerület", "1550597")
    );

    @Resource
    PropertyForRentRepository propertyForRentRepository;

    @Resource
    PropertyForSaleRepository propertyForSaleRepository;

    public List<InvestmentData> getInvestmentRatios() {
        Map<String, List<PropertyForRent>> groupedPropertiesForRent = propertyForRentRepository.getGroupedPropertiesByCity();
        return propertyForSaleRepository.getGroupedPropertiesByCity()
                .entrySet()
                .stream()
                .map(entry ->
                        new InvestmentData(
                                cityName_osmID.get(entry.getKey()),
                                getAveragePriceRatio(entry.getValue()),
                                getAveragePriceRatio(groupedPropertiesForRent.get(entry.getKey())),
                                entry.getKey())
                )
                .sorted(Comparator.comparing(InvestmentData::getRatio))
                .collect(Collectors.toList());

    }

    private <P extends Property> double getAveragePriceRatio(List<P> properties) {
        return properties
                .stream()
                .mapToDouble(property -> property.getPriceRatio())
                .average().orElse(0.0);
    }
}
