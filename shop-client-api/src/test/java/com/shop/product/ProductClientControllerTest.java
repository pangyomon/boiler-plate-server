package com.shop.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ProductClientControllerTest {

    @Autowired
    private ProductClientService productClientService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllTest() {
        // given
//        mockMvc.perform(get("/"))

        // when

        // then
    }
}