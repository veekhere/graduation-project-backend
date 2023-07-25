package com.veekhere.storebase.domain.repository.mapper;

import com.veekhere.storebase.domain.model.StoreModel.*;
import com.veekhere.storebase.domain.repository.entity.StoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StoreEntityMapper {
    public static final StoreEntityMapper MAPPER = Mappers.getMapper(StoreEntityMapper.class);

    public Store map(StoreEntity storeEntity) {
        if (storeEntity == null) {
            return null;
        }

        ProductEntityMapper productEntityMapper = ProductEntityMapper.MAPPER;
        Store store = new Store(
                storeEntity.getId(),
                storeEntity.getName(),
                storeEntity.getDescription(),
                storeEntity.getProducts().stream().map(productEntityMapper::map).toList()
        );

        return store;
    };

    public abstract StoreEntity map(StoreInput bookInput);
}
