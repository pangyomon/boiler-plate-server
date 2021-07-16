package com.shop.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.product.dto.ProductSaveRequestDto;
import com.shop.product.dto.ProductUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.shop.utils.ProductAdminUtil.generateProductSaveRequestDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest() throws Exception {
        String title = "title";
        String description = "description";
        int price = 0;
        String thumbnailImageUrl = "aaa";
        ProductStatus productStatus = ProductStatus.NOT_USE;

        ProductSaveRequestDto requestDto = ProductSaveRequestDto.builder()
                .title(title)
                .description(description)
                .price(price)
                .thumbnailImageUrl(thumbnailImageUrl)
                .productStatus(productStatus)
                .build();

        String savedProductIdStr = mockMvc.perform(post("/api/v1/product")
                .content(new ObjectMapper().writeValueAsString(requestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Long savedProductId = Long.parseLong(savedProductIdStr);

        Product savedProduct = productRepository.findById(savedProductId).get();

        assertThat(savedProduct.getId()).isEqualTo(savedProductId);
        assertThat(savedProduct.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(savedProduct.getDescription()).isEqualTo(requestDto.getDescription());
        assertThat(savedProduct.getThumbnailImageUrl()).isEqualTo(requestDto.getThumbnailImageUrl());
        assertThat(savedProduct.getProductStatus()).isEqualTo(requestDto.getProductStatus());
    }


    @Test
    void updateTest() throws Exception {
        // given
        String changedTitle = "changedTitle";
        String changedDescription = "changedDescription";
        int changedPrice = 0;

        Product product = generateProductSaveRequestDto().toEntity();

        ProductUpdateRequestDto requestDto = ProductUpdateRequestDto.builder()
                .title("changedTitle")
                .description(changedDescription)
                .price(changedPrice)
                .build();

        productRepository.save(product);

        // when
        String changedProductIdStr = mockMvc.perform(put("/api/v1/product/" + product.getId())
                .content(new ObjectMapper().writeValueAsString(requestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        long changedProductId = Long.parseLong(changedProductIdStr);
        Product changedProduct = productRepository.findById(changedProductId).get();

        // then
        assertThat(changedProduct.getId()).isEqualTo(product.getId());
        assertThat(changedProduct.getTitle()).isEqualTo(changedTitle);
        assertThat(changedProduct.getDescription()).isEqualTo(changedDescription);
        assertThat(changedProduct.getPrice()).isEqualTo(changedPrice);
    }
}