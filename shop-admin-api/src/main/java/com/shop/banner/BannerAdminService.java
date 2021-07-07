package com.shop.banner;

import com.shop.banner.dto.BannerAdminResponseDto;
import com.shop.banner.dto.BannerSaveRequestDto;
import com.shop.banner.dto.BannerUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BannerAdminService {

    private final BannerRepository bannerRepository;

    @Transactional(readOnly = true)
    public BannerAdminResponseDto findById(Long id) {
        Banner findBanner =  bannerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "인 배너는 존재하지 않습니다"));

        return new BannerAdminResponseDto(findBanner);
    }

    @Transactional(readOnly = true)
    public List<BannerAdminResponseDto> findAll() {
        return bannerRepository.findAll().stream()
                .map(BannerAdminResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(BannerSaveRequestDto requestDto) {
        return bannerRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BannerUpdateRequestDto requestDto) {
        Banner findBanner =  bannerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "인 배너는 존재하지 않습니다"));

        findBanner.changeTitle(requestDto.getTitle());
        findBanner.changeImageUrl(requestDto.getImageUrl());
        findBanner.changePageUrl(requestDto.getPageUrl());
        findBanner.changeBackgroundColor(requestDto.getBackgroundColor());
        findBanner.changeBannerStatus(requestDto.getBannerStatus());

        return findBanner.getId();
    }

    @Transactional
    public void delete(Long id) {
        Banner findBanner =  bannerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "인 배너는 존재하지 않습니다"));

        bannerRepository.delete(findBanner);
    }
}
