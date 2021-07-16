package com.shop.product;

import com.shop.product.dto.ProductDetailResponseDto;
import com.shop.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductClientController {

    private final ProductClientService productClientService;

    @GetMapping("/api/v1/product")
    public List<ProductResponseDto> getProductList() {
        return productClientService.getProductList();
    }

    @GetMapping("/api/v1/product/{id}")
    public ProductDetailResponseDto getProductDetail(@PathVariable("id") Long id) {
        return productClientService.getProductDetail(id);
    }
}
