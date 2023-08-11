package com.veekhere.storebase.domain.repository.mapper;

import com.veekhere.storebase.domain.model.StoreModel.Store;
import com.veekhere.storebase.domain.model.StoreModel.StoreInput;
import com.veekhere.storebase.domain.repository.entity.StoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = ProductEntityMapper.class)
public abstract class StoreEntityMapper {
    public static final StoreEntityMapper MAPPER = Mappers.getMapper(StoreEntityMapper.class);

    @Mapping(target = "products", qualifiedByName = { "ProductEntityMapper", "mapFromEntity" })
    public abstract Store map(StoreEntity storeEntity);

    public abstract StoreEntity map(StoreInput bookInput);
}
