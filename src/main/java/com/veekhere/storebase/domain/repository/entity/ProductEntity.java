package com.veekhere.storebase.domain.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalDouble;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    UUID id;

    @Column(nullable = false)
    String title;

    String description;

    @Column(nullable = false)
    BigDecimal price;

    @Column(nullable = false)
    Integer availableAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    StoreEntity store;

    transient Integer totalRatings = 0;

    transient Float rating = 0F;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<ProductRatingEntity> ratings;

    public Integer getTotalRatings() {
        return ratings.size();
    }

    public Float getRating() {
        OptionalDouble rating = ratings.stream().mapToLong(value -> value.getRating().longValue()).average();
        return (float) rating.orElse(0);
    }
}
