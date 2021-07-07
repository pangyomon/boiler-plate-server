package com.shop.common;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    @CreationTimestamp
    private LocalDateTime firstCreateDt;

    @UpdateTimestamp
    private LocalDateTime lastUpdateDt;
}
