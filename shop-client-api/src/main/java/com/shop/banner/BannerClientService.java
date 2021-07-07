package com.shop.banner;

import com.shop.banner.dto.BannerClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BannerClientService {

    private final BannerRepository bannerRepository;

    public List<BannerClientResponse> findUseBannerList() {
        return bannerRepository.findBannerByBannerStatus(BannerStatus.USE).stream()
                .map((banner) -> BannerClientResponse.builder()
                        .imageUrl(banner.getImageUrl())
                        .pageUrl(banner.getPageUrl())
                        .backgroundColor(banner.getBackgroundColor())
                        .build())
                .collect(Collectors.toList());
    }
}
