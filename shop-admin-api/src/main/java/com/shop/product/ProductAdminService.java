package com.shop.product;

import com.shop.product.dto.ProductDetailResponseDto;
import com.shop.product.dto.ProductResponseDto;
import com.shop.product.dto.ProductSaveRequestDto;
import com.shop.product.dto.ProductUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductAdminService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDetailResponseDto findById(Long id) {
        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID " + id + "를 가진 상품이 존재하지 않습니다."));

        return new ProductDetailResponseDto(findProduct);
    }

    @Transactional
    public Long createProduct(ProductSaveRequestDto requestDto) {
        return productRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long updateProduct(Long id, ProductUpdateRequestDto requestDto) {
        Product savedProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "인 상품이 존재하지 않습니다."));

        savedProduct.changeTitle(requestDto.getTitle());
        savedProduct.changeDescription(requestDto.getDescription());
        savedProduct.changePrice(requestDto.getPrice());

        return savedProduct.getId();
    }
}
