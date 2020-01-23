package com.invest.demo.controllers;


import com.invest.demo.entities.PropertyForRent;
import com.invest.demo.repositories.PropertyForRentRepository;
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

import static com.invest.demo.controllers.PropertyForSaleControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyForRentControllerTest {

    private PropertyForRent propertyForRent = new PropertyForRent("Elm street", "Szeged", "60", "66", "5", "5", "500 000");
    private PropertyForRent propertyWithSpecialChar = new PropertyForRent("Elm street", "Budaörs", "60", "66", "5", "5", "500 000");

    @Autowired
    private MockMvc mvc;

    @Resource
    PropertyForRentRepository propertyForRentRepository;

    @BeforeEach
    public void cleanDb() throws Exception {
        propertyForRentRepository.deleteAll();
    }

    @Test
    public void getPropertiesWhenEmpty() throws Exception {
        propertyForRentRepository.deleteAll();
        mvc.perform(MockMvcRequestBuilders.get("/properties/rent").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void getPropertiesOneItem() throws Exception {
        propertyForRentRepository.save(propertyForRent);
        sendRequestAndCheckMatch(propertyForRent);
    }

    @Test
    public void getPropertiesWithSpecialChar() throws Exception {
        propertyForRentRepository.save(propertyWithSpecialChar);
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
                .content(asJsonString(propertyForRent))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private ResultActions sendRequestAndCheckMatch(PropertyForRent property) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/properties/rent").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$[0].location", is(property.location)))
                .andExpect(jsonPath("$[0].city", is(property.city)))
                .andExpect(jsonPath("$[0].street", is(property.street)))
                .andExpect(jsonPath("$[0].price", is(property.price)))
                .andExpect(jsonPath("$[0].size", is(property.size)))
                .andExpect(jsonPath("$[0].balconySize", is(property.balconySize)));
    }


}