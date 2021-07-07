package com.shop.banner;

import com.shop.banner.dto.BannerAdminResponseDto;
import com.shop.banner.dto.BannerSaveRequestDto;
import com.shop.banner.dto.BannerUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BannerAdminController {

    private final BannerAdminService bannerAdminService;

    @GetMapping("/api/v1/banner")
    public List<BannerAdminResponseDto> findAll() {
        return bannerAdminService.findAll();
    }

    @PostMapping("/api/v1/banner")
    public Long save(@RequestBody @Valid BannerSaveRequestDto requestDto) {
        return bannerAdminService.save(requestDto);
    }

    @PutMapping("/api/v1/banner/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody @Valid BannerUpdateRequestDto requestDto) {
        return bannerAdminService.update(id, requestDto);
    }
}
