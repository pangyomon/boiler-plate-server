package com.shop.banner.dto;

import com.shop.banner.Banner;
import com.shop.banner.BannerStatus;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class BannerSaveRequestDto {

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
    public BannerSaveRequestDto(
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

    public Banner toEntity() {
        return Banner.builder()
                .title(this.title)
                .imageUrl(this.imageUrl)
                .pageUrl(this.pageUrl)
                .backgroundColor(this.backgroundColor)
                .bannerStatus(this.bannerStatus)
                .build();
    }
}
