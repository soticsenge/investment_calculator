package com.invest.demo;

import com.invest.demo.entities.PropertyForSale;
import com.invest.demo.repositories.PropertyForRentRepository;
import com.invest.demo.repositories.PropertyForSaleRepository;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class InvestmentRatioCalculatorApplication implements CommandLineRunner {

    public static final String baseUrl = "https://ingatlan.com/lista/elado+lakas+budapest";
    @Resource
    private PropertyForSaleRepository propertyForSaleRepository;
    @Resource
    private PropertyForRentRepository propertyForRentRepository;

    public static void main(String[] args) {
        SpringApplication.run(InvestmentRatioCalculatorApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        // propertyForSaleRepository.deleteAll();
      /*  Document doc = Jsoup.connect(baseUrl).get();
        fetchAndProcessLines(Jsoup.connect(baseUrl).get());
        Integer dataNr = Integer.parseInt(doc.select("div.results__number").attr("data-listings-count").replace(" ", ""));
        IntStream.rangeClosed(2, dataNr).forEach(i -> {
            try {
                fetchAndProcessLines(Jsoup.connect(baseUrl + "?page=" + i).get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    private void fetchAndProcessLines(Document doc) {
        Elements propertyLines = doc.select("div.listing");
        for (Element propertyLine : propertyLines) {
            propertyForSaleRepository.save(getPropertyForSale(propertyLine));
        }
    }

    private PropertyForSale getPropertyForSale(Element headline) {
        String[] cityWithStreet = getListingAddresses(headline);
        return new PropertyForSale(
                cityWithStreet[0],
                cityWithStreet[1],
                getDoubleParameter(headline, "listing__data--area-size", "m.*"),
                getDoubleParameter(headline, "price", " M Ft"),
                getBalconySize(headline),
                headline.attr("data-id"),
                headline.getElementsByClass("listing__data--room-count").get(0).text(),
                getDoubleParameter(headline, "price--sqm", " Ft/m2")
        );
    }

    private String[] getListingAddresses(Element headline) {
        return headline
                .getElementsByClass("listing__address")
                .get(0)
                .text()
                .split(", ");
    }

    private Double getBalconySize(Element headline) {
        if (headline.getElementsByClass("listing__data--balcony-size").size() == 1) {
            return Double.parseDouble(
                    headline.getElementsByClass("listing__data--balcony-size")
                            .get(0)
                            .text()
                            .replaceAll("m.*", "")
                            .replaceAll("\\s", ""));
        } else {
            return 0.0;
        }
    }

    private Double getDoubleParameter(Element headline, String className, String placeholder) {
        return Double.parseDouble(
                headline.getElementsByClass(className)
                        .get(0)
                        .text()
                        .replaceAll(placeholder, "")
                        .replaceAll("\\s", ""));
    }

}
