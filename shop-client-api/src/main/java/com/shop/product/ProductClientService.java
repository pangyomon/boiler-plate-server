package com.shop.product;

import com.shop.product.dto.ProductDetailResponseDto;
import com.shop.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductClientService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDetailResponseDto getProductDetail(Long id) {
        Product findProduct = productRepository.findProductByIdAndProductStatus(id, ProductStatus.USE)
                .orElseThrow(() -> new IllegalArgumentException("ID " + id + " 인 제품은 존재하지 않습니다."));

        return new ProductDetailResponseDto(findProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProductList() {
        return productRepository.findAllByProductStatus(ProductStatus.USE)
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }
}
