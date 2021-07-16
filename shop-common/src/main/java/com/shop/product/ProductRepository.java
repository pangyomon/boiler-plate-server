package com.shop.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findProductByIdAndProductStatus(Long id, ProductStatus productStatus);

    public List<Product> findAllByProductStatus(ProductStatus productStatus);
}
