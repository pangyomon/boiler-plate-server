package com.shop.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    @AfterEach
    @Transactional
    public void cleanUp() {
        productRepository.deleteAll();
    }


    @Test
    @Transactional
    public void saveTest() {
        // given
        String title = "productTitle";
        String description = "productDescription";
        String thumbnailImageUrl = "productThumbnailImageUrl";
        int price = 100;
        ProductStatus productStatus = ProductStatus.NOT_USE;

        Product product = Product.builder()
                .title(title)
                .description(description)
                .thumbnailImageUrl(thumbnailImageUrl)
                .price(price)
                .productStatus(productStatus)
                .build();

        // when
        Long savedProductId = productRepository.save(product).getId();
        Product savedProduct = productRepository.findById(savedProductId).get();

        // then
        assertThat(savedProduct.getId()).isEqualTo(product.getId());
        assertThat(savedProduct.getTitle()).isEqualTo(title);
        assertThat(savedProduct.getDescription()).isEqualTo(description);
        assertThat(savedProduct.getThumbnailImageUrl()).isEqualTo(thumbnailImageUrl);
        assertThat(savedProduct.getPrice()).isEqualTo(price);
        assertThat(savedProduct.getProductStatus()).isEqualTo(productStatus);
    }


    @Test
    @Transactional
    public void saveWithImageTest() {
        String title = "productTitle";
        String description = "productDescription";
        String thumbnailImageUrl = "productThumbnailImageUrl";
        int price = 100;
        ProductStatus productStatus = ProductStatus.NOT_USE;

        String productImageUrl = "http://naver.com";

        Product product = Product.builder()
                .title(title)
                .description(description)
                .thumbnailImageUrl(thumbnailImageUrl)
                .price(price)
                .productStatus(productStatus)
                .build();
        ProductImage productImage = ProductImage.builder()
                .imageUrl(productImageUrl)
                .build();

        product.addProductImage(productImage);
        productRepository.save(product);

        Product savedProduct = productRepository.findById(product.getId()).get();
        ProductImage savedProductImage = savedProduct.getProductImageList().get(0);

        assertThat(savedProductImage.getId()).isEqualTo(productImage.getId());
        assertThat(savedProductImage.getImageUrl()).isEqualTo(productImage.getImageUrl());
    }
}