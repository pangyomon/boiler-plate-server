package com.shop.banner;

import com.shop.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Banner extends BaseTimeEntity {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(nullable = false)
    private String backgroundColor;

    private String imageUrl;

    private String pageUrl;

    @Enumerated(EnumType.STRING)
    private BannerStatus bannerStatus;

    @Builder
    public Banner(String title, String backgroundColor, String imageUrl, String pageUrl, BannerStatus bannerStatus) {
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.imageUrl = imageUrl;
        this.pageUrl = pageUrl;
        this.bannerStatus = bannerStatus;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void changePageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public void changeBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void changeBannerStatus(BannerStatus bannerStatus) {
        this.bannerStatus = bannerStatus;
    }
}
