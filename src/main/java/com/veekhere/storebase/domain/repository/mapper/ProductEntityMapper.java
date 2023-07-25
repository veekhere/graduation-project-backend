package com.veekhere.storebase.domain.repository.mapper;

import com.veekhere.storebase.domain.model.ProductModel.*;
import com.veekhere.storebase.domain.repository.entity.ProductEntity;
import com.veekhere.storebase.domain.repository.entity.ProductProjectionEntity;
import com.veekhere.storebase.domain.repository.entity.StoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductEntityMapper {
    public static final ProductEntityMapper MAPPER = Mappers.getMapper(ProductEntityMapper.class);

    public StoreEntity map(UUID id) {
        if (id == null) {
            return null;
        }
        return new StoreEntity(id);
    }

    public abstract Product map(ProductEntity productEntity);

    public abstract ProductProjection map(ProductProjectionEntity productEntity);

    public abstract ProductEntity map(ProductInput bookInput);
}
