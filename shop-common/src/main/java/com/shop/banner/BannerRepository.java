package com.shop.banner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

    public List<Banner> findBannerByBannerStatus(BannerStatus bannerStatus);
}
