package com.shop.product;

import com.shop.product.dto.ProductResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shop.utills.ProductUtil.generateProductEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProductClientServiceTest {

    @Autowired
    private ProductClientService productClientService;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @AfterEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void findAllTest() {
        // given
        Product product1 = generateProductEntity();
        Product product2 = generateProductEntity();
        Product product3 = generateProductEntity();
        Product product4 = generateProductEntity();
        Product product5 = generateProductEntity();

        List<Product> productList = new ArrayList<>(
                Arrays.asList(
                        product1,
                        product2,
                        product3,
                        product4,
                        product5
                )
        );
        productRepository.saveAll(productList);

        // when
        List<ProductResponseDto> responseDto = productClientService.getProductList();

        // then
        assertThat(responseDto.size()).isEqualTo(5);
    }

    @Test
    public void findOnlyUseTest() {
        // given
        Product product1 = generateProductEntity();
        Product product2 = generateProductEntity();
        Product product3 = generateProductEntity();
        Product product4 = generateProductEntity();
        Product product5 = generateProductEntity();

        product1.changeStatus(ProductStatus.NOT_USE);

        List<Product> productList = new ArrayList<>(
                Arrays.asList(
                        product1,
                        product2,
                        product3,
                        product4,
                        product5
                )
        );
        productRepository.saveAll(productList);

        // when
        List<ProductResponseDto> requestDto = productClientService.getProductList();

        assertThat(requestDto.size()).isEqualTo(4);
    }
}