package com.invest.demo.tasks;

import com.invest.demo.entities.PropertyForRent;
import com.invest.demo.entities.PropertyForSale;
import com.invest.demo.repositories.LocationDataRepository;
import com.invest.demo.repositories.PropertyForRentRepository;
import com.invest.demo.repositories.PropertyForSaleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class PropertiesForSaleLoader extends Thread {

    public static final String baseUrlSale = "https://ingatlan.com/lista/elado+lakas";
    public static final String baseUrlRent = "https://ingatlan.com/lista/kiado+lakas";
    @Resource
    private PropertyForSaleRepository propertyForSaleRepository;
    @Resource
    private PropertyForRentRepository propertyForRentRepository;

    Integer saveNr = 1;

    public void run() {
        loadPropertiesForRent();
        loadPropertiesForSale();

    }

    private void loadPropertiesForSale() {
        try {
            propertyForSaleRepository.deleteAll();
            Document doc = null;

            doc = Jsoup.connect(baseUrlSale).get();

            fetchAndProcessLinesSale(doc);
            Integer dataNr = Integer.parseInt(doc.select("div.results__number").attr("data-listings-count").replace(" ", ""));
            for (int i = 1; i <= dataNr / 20 + 1; i++) {
                fetchAndProcessLinesSale(Jsoup.connect(baseUrlSale + "?page=" + i).get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPropertiesForRent() {
        try {
            propertyForRentRepository.deleteAll();
            Document doc = null;

            doc = Jsoup.connect(baseUrlRent).get();

            fetchAndProcessLinesRent(doc);
            Integer dataNr = Integer.parseInt(doc.select("div.results__number").attr("data-listings-count").replace(" ", ""));
            for (int i = 1; i <= dataNr / 20 + 1; i++) {
                fetchAndProcessLinesRent(Jsoup.connect(baseUrlRent + "?page=" + i).get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchAndProcessLinesSale(Document doc) {
        Elements propertyLines = doc.select("div.listing");
        for (Element propertyLine : propertyLines) {
            propertyForSaleRepository.save(getPropertyForSale(propertyLine));
            System.out.println(saveNr++ + "items saved\n");
        }
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fetchAndProcessLinesRent(Document doc) {
        Elements propertyLines = doc.select("div.listing");
        for (Element propertyLine : propertyLines) {
            propertyForRentRepository.save(getPropertyForRent(propertyLine));
            System.out.println(saveNr++ + "items saved\n");
        }
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private PropertyForRent getPropertyForRent(Element headline) {
        String[] cityWithStreet = getListingAddresses(headline);
        return new PropertyForRent(
                (cityWithStreet.length >= 1) ? cityWithStreet[0] : "",
                (cityWithStreet.length == 2) ? cityWithStreet[1] : "",
                getDoubleParameter(headline, "listing__data--area-size", "m.*"),
                getDoubleParameter(headline, "price", " Ft/hó"),
                getBalconySize(headline),
                headline.attr("data-id"),
                headline.getElementsByClass("listing__data--room-count").get(0).text(),
                headline.getElementsByClass("listing__image").isEmpty() ? "" :
                        headline.getElementsByClass("listing__image").get(0).attr("src")
        );
    }

    private PropertyForSale getPropertyForSale(Element headline) {
        String[] cityWithStreet = getListingAddresses(headline);
        return new PropertyForSale(
                (cityWithStreet.length >= 1) ? cityWithStreet[0] : "",
                (cityWithStreet.length == 2) ? cityWithStreet[1] : "",
                getDoubleParameter(headline, "listing__data--area-size", "m.*"),
                getDoubleParameter(headline, "price", " M Ft"),
                getBalconySize(headline),
                headline.attr("data-id"),
                headline.getElementsByClass("listing__data--room-count").get(0).text(),
                getDoubleParameter(headline, "price--sqm", " Ft/m2"),
                headline.getElementsByClass("listing__image").isEmpty() ? "" :
                        headline.getElementsByClass("listing__image").get(0).attr("src")
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

    /*locationDataRepository.findAll().forEach(locationData -> {
            System.out.println(locationData.getId());
            if (locationData.getGeoJson() == null) {
                try {
                    final String uri = "https://nominatim.openstreetmap.org/search?q=" + locationData.getId() + "&format=geojson&polygon_geojson=1'";
                    RestTemplate restTemplate = new RestTemplate();
                    String result = restTemplate.getForObject(uri, String.class);
                    System.out.println(result);
                    locationData.setGeoJson(result);
                    locationDataRepository.save(locationData);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    System.out.println("Could not load geoJson for " + locationData.getId());
                    e.printStackTrace();
                }
            }
        });*/
}
