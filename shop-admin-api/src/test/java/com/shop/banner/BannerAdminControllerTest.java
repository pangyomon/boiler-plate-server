package com.shop.banner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.banner.dto.BannerAdminResponseDto;
import com.shop.banner.dto.BannerSaveRequestDto;
import com.shop.banner.dto.BannerUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.shop.utils.BannerAdminUtil.generateBannerSaveRequestDto;
import static com.shop.utils.BannerAdminUtil.generateUpdateRequestDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BannerAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
    public void saveTest() throws Exception {
        // given
        BannerSaveRequestDto requestDto = generateBannerSaveRequestDto();

        // when
        String savedBannerIdStr = mockMvc.perform(post("/api/v1/banner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Long savedBannerId = Long.parseLong(savedBannerIdStr);
        BannerAdminResponseDto responseDto = bannerAdminService.findById(savedBannerId);

        // then
        assertThat(responseDto.getId()).isEqualTo(savedBannerId);
        assertThat(responseDto.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(responseDto.getImageUrl()).isEqualTo(requestDto.getImageUrl());
        assertThat(responseDto.getBackgroundColor()).isEqualTo(requestDto.getBackgroundColor());
        assertThat(responseDto.getBannerStatus()).isEqualTo(responseDto.getBannerStatus());
    }

    @Test
    public void updateTest() throws Exception {
        // given
        BannerSaveRequestDto saveRequestDto = generateBannerSaveRequestDto();
        BannerUpdateRequestDto updateRequestDto = generateUpdateRequestDto();

        // when
        String savedBannerIdStr = mockMvc.perform(post("/api/v1/banner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(saveRequestDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Long savedBannerId = Long.parseLong(savedBannerIdStr);

        String updatedBannerIdStr = mockMvc.perform(put("/api/v1/banner/" + savedBannerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateRequestDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Long updatedBannerId = Long.parseLong(updatedBannerIdStr);
        BannerAdminResponseDto responseDto = bannerAdminService.findById(updatedBannerId);

        // then
        assertThat(responseDto.getId()).isEqualTo(savedBannerId).isEqualTo(updatedBannerId);
        assertThat(responseDto.getTitle()).isNotEqualTo(saveRequestDto.getTitle()).isEqualTo(updateRequestDto.getTitle());
        assertThat(responseDto.getImageUrl()).isNotEqualTo(saveRequestDto.getImageUrl()).isEqualTo(updateRequestDto.getImageUrl());
        assertThat(responseDto.getPageUrl()).isNotEqualTo(saveRequestDto.getPageUrl()).isEqualTo(updateRequestDto.getPageUrl());
        assertThat(responseDto.getBackgroundColor()).isNotEqualTo(saveRequestDto.getBackgroundColor()).isEqualTo(updateRequestDto.getBackgroundColor());
        assertThat(responseDto.getBannerStatus()).isEqualTo(updateRequestDto.getBannerStatus());
    }
}