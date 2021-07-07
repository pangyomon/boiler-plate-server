package com.shop.banner.dto;

import com.shop.banner.BannerStatus;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
public class BannerUpdateRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private String pageUrl;

    @NotBlank
    private String backgroundColor;

    private BannerStatus bannerStatus;

    @Builder
    public BannerUpdateRequestDto(
            String title,
            String imageUrl,
            String pageUrl,
            String backgroundColor,
            BannerStatus bannerStatus
    ) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.pageUrl = pageUrl;
        this.backgroundColor = backgroundColor;
        this.bannerStatus = bannerStatus;
    }
}
