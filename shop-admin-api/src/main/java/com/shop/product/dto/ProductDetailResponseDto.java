package com.shop.product.dto;

import com.shop.product.Product;
import com.shop.product.ProductImage;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductDetailResponseDto extends ProductResponseDto {

    private String description;

    private List<ProductImage> productImageList = new ArrayList<>();

    public ProductDetailResponseDto(Product product) {
        super(product);

        this.description = product.getDescription();
        this.productImageList = product.getProductImageList();
    }
}
