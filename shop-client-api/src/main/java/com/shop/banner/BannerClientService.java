package com.shop.banner;

import com.shop.banner.dto.BannerClientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BannerClientService {

    private final BannerRepository bannerRepository;

    public List<BannerClientResponseDto> findUseBannerList() {
        return bannerRepository.findBannerByBannerStatus(BannerStatus.USE).stream()
                .map((banner) -> BannerClientResponseDto.builder()
                        .imageUrl(banner.getImageUrl())
                        .pageUrl(banner.getPageUrl())
                        .backgroundColor(banner.getBackgroundColor())
                        .build())
                .collect(Collectors.toList());
    }
}
