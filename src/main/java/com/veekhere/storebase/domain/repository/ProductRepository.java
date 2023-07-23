package com.veekhere.storebase.domain.repository;

import com.veekhere.storebase.domain.model.ProductModel.*;
import com.veekhere.storebase.domain.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query(value = "SELECT * FROM products p " +
            "WHERE (" +
                "( LENGTH(LOWER(COALESCE(:#{#filter.title}, ''))) > 0 AND POSITION(LOWER(COALESCE(:#{#filter.title}, '')) in LOWER(p.title)) > 0 ) " +
                "OR " +
                "( LENGTH(COALESCE(:#{#filter.title}, '')) = 0 AND p.title = p.title ) " +
            ") " +
            "AND " +
            "( " +
                "( CAST(COALESCE(:#{#filter.priceFrom}, 0) AS NUMERIC) > 0 AND p.price >= CAST(COALESCE(:#{#filter.priceFrom}, 0) AS NUMERIC) )" +
                "OR " +
                "( CAST(COALESCE(:#{#filter.priceFrom}, 0) AS NUMERIC) <= 0 AND p.price = p.price ) " +
            ") " +
            "AND " +
            "( " +
                "( CAST(COALESCE(:#{#filter.priceTo}, 0) AS NUMERIC) > 0 AND p.price <= CAST(COALESCE(:#{#filter.priceTo}, 0) AS NUMERIC) )" +
                "OR " +
                "( CAST(COALESCE(:#{#filter.priceTo}, 0) AS NUMERIC) <= 0 AND p.price = p.price ) " +
            ") " +
            "AND " +
            "( " +
                "( CAST(COALESCE(:#{#filter.isAvailable}, FALSE) AS BOOL) IS TRUE AND p.available_amount > 0 )" +
                "OR " +
                "( CAST(COALESCE(:#{#filter.isAvailable}, FALSE) AS BOOL) IS FALSE AND p.available_amount = p.available_amount ) " +
            ");"
            , nativeQuery = true)
    List<ProductEntity> searchProducts(@Param("filter") ProductFilter filter);
}
