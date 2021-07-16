package com.shop.product.dto;


import com.shop.banner.BannerStatus;
import com.shop.product.Product;
import com.shop.product.ProductStatus;
import lombok.Getter;

@Getter
public class ProductResponseDto {

    private Long id;

    private String title;

    private String thumbnailimageUrl;

    private int price;

    private ProductStatus productStatus;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.thumbnailimageUrl = product.getThumbnailImageUrl();
        this.price = product.getPrice();
        this.productStatus = product.getProductStatus();
    }
}
