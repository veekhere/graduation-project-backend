package com.veekhere.storebase.domain.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_rating")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    UUID id;

    @OneToOne(cascade = CascadeType.MERGE)
    ProductEntity product;

    @Column(nullable = false)
    Integer rating;

    String comment;

    public void setProduct(UUID id) {
        this.product = new ProductEntity(id);
    }
}
