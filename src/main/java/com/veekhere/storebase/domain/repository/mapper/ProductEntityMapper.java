package com.veekhere.storebase.domain.repository.mapper;

import com.veekhere.storebase.domain.model.ProductModel.*;
import com.veekhere.storebase.domain.repository.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductEntityMapper {
    public static final ProductEntityMapper MAPPER = Mappers.getMapper(ProductEntityMapper.class);

    public abstract ProductEntity map(Product product);

    public abstract Product map(ProductEntity productEntity);

    public abstract ProductEntity map(ProductInput bookInput);
}
