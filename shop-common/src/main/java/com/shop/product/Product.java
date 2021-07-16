package com.shop.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private int price;

    private String thumbnailImageUrl;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<ProductImage> productImageList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Builder
    public Product(
            String title,
            String description,
            String thumbnailImageUrl,
            int price,
            ProductStatus productStatus
    ) {
        this.title = title;
        this.description = description;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.price = price;
        this.productStatus = productStatus;
    }


    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public void changeStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public void addProductImage(ProductImage productImage) {
        productImageList.add(productImage);
    }

    public void removeProductImage(ProductImage productImage) {
        productImageList.remove(productImage);
    }
}
