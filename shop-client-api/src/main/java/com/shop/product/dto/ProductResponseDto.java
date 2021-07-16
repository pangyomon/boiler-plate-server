package com.shop.product.dto;

import com.shop.product.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {

    private Long id;
    private String title;
    private int price;
    private String thumbnailUrl;


    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.thumbnailUrl = product.getThumbnailImageUrl();
    }
}
