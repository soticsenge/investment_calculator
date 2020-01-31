package com.invest.demo.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.invest.demo.entities.PropertyForSale;
import com.invest.demo.repositories.PropertyForSaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyForSaleControllerTest {

    private PropertyForSale propertyForSale = new PropertyForSale("Elm street", "Szeged", 60.0, 66.0, 66000.0, "5", "5", 500.0);
    private PropertyForSale propertyWithSpecialChar = new PropertyForSale("Elm street", "Budaörs", 60.0, 66.0, 660000.0, "5", "5", 500000.0);

    @Autowired
    private MockMvc mvc;

    @Resource
    PropertyForSaleRepository propertyForSaleRepository;

    @BeforeEach
    public void cleanDb() throws Exception {
        propertyForSaleRepository.deleteAll();
    }

    @Test
    public void getPropertiesWhenEmpty() throws Exception {
        propertyForSaleRepository.deleteAll();
        mvc.perform(MockMvcRequestBuilders.get("/properties/sale").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void getPropertiesOneItem() throws Exception {
        propertyForSaleRepository.save(propertyForSale);
        sendRequestAndCheckMatch(propertyForSale);
    }

    @Test
    public void getPropertiesWithSpecialChar() throws Exception {
        propertyForSaleRepository.save(propertyWithSpecialChar);
        sendRequestAndCheckMatch(propertyWithSpecialChar);

    }

    @Test
    public void savePropertyBadFormat() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/properties/sale")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void savePropertySuccess() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/properties/sale")
                .content(asJsonString(propertyForSale))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private ResultActions sendRequestAndCheckMatch(PropertyForSale property) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/properties/sale").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$[0].location", is(property.getLocation())))
                .andExpect(jsonPath("$[0].city", is(property.getCity())))
                .andExpect(jsonPath("$[0].street", is(property.getStreet())))
                .andExpect(jsonPath("$[0].price", is(property.getPrice())))
                .andExpect(jsonPath("$[0].size", is(property.getSize())))
                .andExpect(jsonPath("$[0].balconySize", is(property.getBalconySize())))
                .andExpect(jsonPath("$[0].priceRatio", is(property.getPriceRatio())));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}