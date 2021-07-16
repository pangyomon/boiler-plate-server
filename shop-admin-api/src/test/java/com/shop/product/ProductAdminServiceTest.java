package com.shop.product;

import com.shop.product.dto.ProductDetailResponseDto;
import com.shop.product.dto.ProductResponseDto;
import com.shop.product.dto.ProductSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.shop.utils.ProductAdminUtil.generateProductSaveRequestDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ProductAdminServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductAdminService productAdminService;

    @AfterEach
    @Transactional
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        // given
        ProductSaveRequestDto requestDto = generateProductSaveRequestDto();

        // when
        Long savedProductId = productAdminService.createProduct(requestDto);
        ProductDetailResponseDto responseDto = productAdminService.findById(savedProductId);

        // then
        assertThat(responseDto.getId()).isEqualTo(savedProductId);
    }

    @Test
    void findByIdTest() {
        // given
        ProductSaveRequestDto requestDto = generateProductSaveRequestDto();
        Long savedProductId = productAdminService.createProduct(requestDto);

        // when
        ProductDetailResponseDto responseDto = productAdminService.findById(savedProductId);

        // then
        assertThat(responseDto.getId()).isEqualTo(savedProductId);
        assertThat(responseDto.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(responseDto.getDescription()).isEqualTo(requestDto.getDescription());
        assertThat(responseDto.getThumbnailimageUrl()).isEqualTo(requestDto.getThumbnailImageUrl());
        assertThat(responseDto.getProductStatus()).isEqualTo(requestDto.getProductStatus());
    }

    @Test
    void productNotFoundException() {
        // given
        ProductSaveRequestDto requestDto = generateProductSaveRequestDto();

        // when
        Long savedProductId = productAdminService.createProduct(requestDto);


        // then
        assertThatThrownBy(() -> productAdminService.findById(savedProductId + 1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("인 상품이 존재하지 않습니다.");
    }

    @Test
    void findAllTest() {
        // given
        productAdminService.createProduct(generateProductSaveRequestDto());
        productAdminService.createProduct(generateProductSaveRequestDto());
        productAdminService.createProduct(generateProductSaveRequestDto());

        // when
        List<ProductResponseDto> responseDto = productAdminService.findAll();

        // then
        assertThat(responseDto.size()).isEqualTo(3);
    }
}