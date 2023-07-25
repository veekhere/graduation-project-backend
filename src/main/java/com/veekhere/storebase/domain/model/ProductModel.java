package com.veekhere.storebase.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductModel {

    public record Product(
            UUID id,
            String title,
            Float rating,
            String description,
            BigDecimal price,
            Integer availableAmount) {}

    public record ProductInput(
            UUID id, String title, String description, BigDecimal price, Integer availableAmount, UUID store) {}

    public record ProductProjection(UUID id, String title, BigDecimal price, Integer availableAmount, String storeName) {}

    public record ProductFilter(UUID store, String title, BigDecimal priceFrom, BigDecimal priceTo, Boolean isAvailable) {}

    public record ProductProjectionFilter(String title, BigDecimal priceFrom, BigDecimal priceTo, Boolean isAvailable) {}
}
