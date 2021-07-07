package com.shop.banner;

import com.shop.banner.dto.BannerAdminResponseDto;
import com.shop.banner.dto.BannerSaveRequestDto;
import com.shop.banner.dto.BannerUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.shop.banner.utils.GenerateBannerUtil.generateSaveRequestDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class BannerAdminServiceTest {

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private BannerAdminService bannerAdminService;

    @AfterEach
    @Transactional
    public void cleanUp() {
        bannerRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        //given
        BannerSaveRequestDto requestDto = generateSaveRequestDto();

        // when
        Long savedBannerId = bannerAdminService.save(requestDto);
        BannerAdminResponseDto responseDto = bannerAdminService.findById(savedBannerId);

        // then
        assertThat(responseDto.getId()).isEqualTo(savedBannerId);
        assertThat(responseDto.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(responseDto.getImageUrl()).isEqualTo(requestDto.getImageUrl());
        assertThat(responseDto.getPageUrl()).isEqualTo(requestDto.getPageUrl());
        assertThat(responseDto.getBackgroundColor()).isEqualTo(requestDto.getBackgroundColor());
        assertThat(responseDto.getBannerStatus()).isEqualTo(requestDto.getBannerStatus());
    }

    @Test
    public void updateTest() {
        // given
        BannerSaveRequestDto requestDto = generateSaveRequestDto();
        Long savedBannerId = bannerAdminService.save(requestDto);

        String changeTitle = "changedTitle";
        String changeImageUrl = "changedImageUrl";
        String changePageUrl = "changedPageUrl";
        String changeBackgroundColor = "changedBackgroundColor";
        BannerStatus changeBannerStatus = BannerStatus.USE;


        // when
        BannerUpdateRequestDto updateRequestDto = BannerUpdateRequestDto.builder()
                .title(changeTitle)
                .imageUrl(changeImageUrl)
                .pageUrl(changePageUrl)
                .backgroundColor(changeBackgroundColor)
                .bannerStatus(changeBannerStatus)
                .build();

        Long updatedBannerId = bannerAdminService.update(savedBannerId, updateRequestDto);
        BannerAdminResponseDto updateResponseDto = bannerAdminService.findById(updatedBannerId);

        // then
        assertThat(updateResponseDto.getId()).isEqualTo(savedBannerId).isEqualTo(updatedBannerId);
        assertThat(updateResponseDto.getTitle()).isNotEqualTo(requestDto.getTitle()).isEqualTo(changeTitle);
        assertThat(updateResponseDto.getImageUrl()).isNotEqualTo(requestDto.getImageUrl()).isEqualTo(changeImageUrl);
        assertThat(updateResponseDto.getPageUrl()).isNotEqualTo(requestDto.getPageUrl()).isEqualTo(changePageUrl);
        assertThat(updateResponseDto.getBackgroundColor()).isNotEqualTo(requestDto.getBackgroundColor()).isEqualTo(changeBackgroundColor);
        assertThat(updateResponseDto.getBannerStatus()).isNotEqualTo(requestDto.getBannerStatus()).isEqualTo(changeBannerStatus);
    }

    @Test
    public void deleteTest() {
        // given
        BannerSaveRequestDto requestDto = generateSaveRequestDto();
        Long savedBannerId = bannerAdminService.save(requestDto);

        // when
        bannerAdminService.delete(savedBannerId);

        assertThatThrownBy(() -> bannerAdminService.findById(savedBannerId + 1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("인 배너는 존재하지 않습니다");
    }

    @Test
    public void findIdExceptionTest() {
        // when
        Long savedBannerId = bannerAdminService.save(generateSaveRequestDto());

        // then
        // ID로 조회되지 않아서 Exception 이 발생해야 합니다
        assertThatThrownBy(() -> bannerAdminService.findById(savedBannerId + 1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("인 배너는 존재하지 않습니다");
    }


}