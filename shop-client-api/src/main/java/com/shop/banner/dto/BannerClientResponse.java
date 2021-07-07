package com.shop.banner.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BannerClientResponse {

    private String imageUrl;
    private String pageUrl;
    private String backgroundColor;

    @Builder
    public BannerClientResponse(String imageUrl, String pageUrl, String backgroundColor) {
        this.imageUrl = imageUrl;
        this.pageUrl = pageUrl;
        this.backgroundColor = backgroundColor;
    }
}
