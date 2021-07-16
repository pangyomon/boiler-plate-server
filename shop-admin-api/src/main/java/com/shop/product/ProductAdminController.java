package com.shop.product;

import com.shop.product.dto.ProductDetailResponseDto;
import com.shop.product.dto.ProductResponseDto;
import com.shop.product.dto.ProductSaveRequestDto;
import com.shop.product.dto.ProductUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductAdminController {

    private final ProductAdminService productAdminService;

    @GetMapping("/api/v1/product")
    public List<ProductResponseDto> findAll() {
        return productAdminService.findAll();
    }

    @GetMapping("/api/v1/product/{id}")
    public ProductDetailResponseDto findById(@PathVariable("id") Long id) {
        return productAdminService.findById(id);
    }

    @PostMapping("/api/v1/product")
    public Long createProduct(@RequestBody @Valid ProductSaveRequestDto requestDto) {
        return productAdminService.createProduct(requestDto);
    }

    @PutMapping("/api/v1/product/{id}")
    public Long updateProduct(@PathVariable("id") Long id, @RequestBody @Valid ProductUpdateRequestDto requestDto) {
        return productAdminService.updateProduct(id, requestDto);
    }
}
