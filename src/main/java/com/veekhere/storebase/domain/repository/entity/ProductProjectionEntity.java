package com.veekhere.storebase.domain.repository.entity;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductProjectionEntity {
    UUID getId();

    String getTitle();

    BigDecimal getPrice();

    Integer getAvailableAmount();

    String getStoreName();
}
