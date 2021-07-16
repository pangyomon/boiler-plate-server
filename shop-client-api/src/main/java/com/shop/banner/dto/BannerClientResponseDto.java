package com.shop.banner.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BannerClientResponseDto {

    private String imageUrl;
    private String pageUrl;
    private String backgroundColor;

    @Builder
    public BannerClientResponseDto(String imageUrl, String pageUrl, String backgroundColor) {
        this.imageUrl = imageUrl;
        this.pageUrl = pageUrl;
        this.backgroundColor = backgroundColor;
    }
}
