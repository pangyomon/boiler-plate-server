package com.shop.utills;

import com.github.javafaker.Faker;
import com.shop.product.Product;
import com.shop.product.ProductStatus;

public class ProductUtil {

    public static Product generateProductEntity() {
        Faker faker = new Faker();

        String title = faker.lorem().sentence();
        String description = faker.lorem().sentence();
        String thumbnailImageUrl = faker.internet().image();;
        int price = faker.number().numberBetween(0, 10000000);
        ProductStatus productStatus = ProductStatus.USE;

        return Product.builder()
                .title(title)
                .description(description)
                .thumbnailImageUrl(thumbnailImageUrl)
                .price(price)
                .productStatus(productStatus)
                .build();
    }
}
