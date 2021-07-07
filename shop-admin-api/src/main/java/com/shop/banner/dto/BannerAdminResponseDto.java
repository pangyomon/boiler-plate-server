package com.shop.banner.dto;

import com.shop.banner.Banner;
import com.shop.banner.BannerStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BannerAdminResponseDto {

    private Long id;
    private String title;
    private String backgroundColor;
    private String imageUrl;
    private String pageUrl;
    private BannerStatus bannerStatus;
    private LocalDateTime firstCreateDt;
    private LocalDateTime lastUpdateDt;


    public BannerAdminResponseDto(Banner banner) {
        this.id = banner.getId();
        this.title = banner.getTitle();
        this.backgroundColor = banner.getBackgroundColor();
        this.imageUrl = banner.getImageUrl();
        this.pageUrl = banner.getPageUrl();
        this.bannerStatus = banner.getBannerStatus();
        this.firstCreateDt = banner.getFirstCreateDt();
        this.lastUpdateDt = banner.getLastUpdateDt();
    }
}
