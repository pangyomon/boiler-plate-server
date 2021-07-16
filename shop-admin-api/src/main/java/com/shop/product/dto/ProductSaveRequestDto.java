package com.shop.product.dto;

import com.shop.product.Product;
import com.shop.product.ProductStatus;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class ProductSaveRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Min(value = 0, message = "제품의 가격은 0원 이상이여합니다.")
    private int price;

    @NotBlank
    private String thumbnailImageUrl;

    private ProductStatus productStatus;

    @Builder
    public ProductSaveRequestDto(
            String title,
            String description,
            int price,
            String thumbnailImageUrl,
            ProductStatus productStatus
    ) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.productStatus = productStatus;
    }

    public Product toEntity() {
        return Product.builder()
                .title(title)
                .description(description)
                .price(price)
                .thumbnailImageUrl(thumbnailImageUrl)
                .productStatus(productStatus)
                .build();
    }
}