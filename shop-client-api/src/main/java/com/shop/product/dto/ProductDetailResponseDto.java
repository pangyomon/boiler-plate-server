package com.shop.product.dto;

import com.shop.product.Product;
import com.shop.product.ProductImage;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductDetailResponseDto extends ProductResponseDto {

    public String description;
    public List<ProductImage> productImageList;

    public ProductDetailResponseDto(Product product) {
        super(product);

        this.description = product.getDescription();
        this.productImageList = product.getProductImageList();
    }
}
