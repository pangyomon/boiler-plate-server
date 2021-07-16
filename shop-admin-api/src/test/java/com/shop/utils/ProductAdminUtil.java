package com.shop.utils;

import com.github.javafaker.Faker;
import com.shop.product.ProductStatus;
import com.shop.product.dto.ProductSaveRequestDto;

public class ProductAdminUtil {

    public static ProductSaveRequestDto generateProductSaveRequestDto() {
        Faker faker = new Faker();

        String title = faker.lorem().sentence();
        String thumbnailImageUrl = faker.internet().image();;
        String description = faker.lorem().sentence();
        int price = faker.number().numberBetween(0, 10000000);
        ProductStatus productStatus = ProductStatus.USE;

        return ProductSaveRequestDto.builder()
                .thumbnailImageUrl(thumbnailImageUrl)
                .price(price)
                .title(title)
                .productStatus(productStatus)
                .build();
    }
}
