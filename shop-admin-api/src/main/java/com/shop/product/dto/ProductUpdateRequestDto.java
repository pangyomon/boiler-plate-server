package com.shop.product.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
public class ProductUpdateRequestDto {

    @NotEmpty
    String title;

    @NotEmpty
    String description;

    @Min(0)
    int price;

    @Builder
    public ProductUpdateRequestDto(String title, String description, int price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
