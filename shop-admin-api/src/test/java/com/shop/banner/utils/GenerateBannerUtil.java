package com.shop.banner.utils;

import com.github.javafaker.Faker;
import com.shop.banner.BannerStatus;
import com.shop.banner.dto.BannerSaveRequestDto;
import com.shop.banner.dto.BannerUpdateRequestDto;
import net.bytebuddy.utility.RandomString;

public class GenerateBannerUtil {

    public static BannerSaveRequestDto generateSaveRequestDto() {
        Faker faker = new Faker();

        // given
        String title = faker.lorem().sentence();
        String backgroundColor = faker.color().hex();
        String imageUrl = faker.internet().image();
        String pageUrl = '/' + faker.lorem().word();
        BannerStatus bannerStatus = BannerStatus.NOT_USE;


        return BannerSaveRequestDto.builder()
                .title(title)
                .backgroundColor(backgroundColor)
                .imageUrl(imageUrl)
                .pageUrl(pageUrl)
                .bannerStatus(bannerStatus)
                .build();
    }

    public static BannerUpdateRequestDto generateUpdateRequestDto() {
        Faker faker = new Faker();

        // given
        String title = faker.lorem().sentence();
        String backgroundColor = faker.color().hex();
        String imageUrl = faker.internet().image();
        String pageUrl = '/' + faker.lorem().word();
        BannerStatus bannerStatus = BannerStatus.NOT_USE;


        return BannerUpdateRequestDto.builder()
                .title(title)
                .backgroundColor(backgroundColor)
                .imageUrl(imageUrl)
                .pageUrl(pageUrl)
                .bannerStatus(bannerStatus)
                .build();
    }
}
