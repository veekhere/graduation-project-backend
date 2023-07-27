package com.veekhere.storebase.domain.repository.mapper;

import com.veekhere.storebase.domain.model.ProductRatingModel.ProductRating;
import com.veekhere.storebase.domain.model.ProductRatingModel.ProductRatingInput;
import com.veekhere.storebase.domain.repository.entity.ProductRatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductRatingEntityMapper {
    public static final ProductRatingEntityMapper MAPPER = Mappers.getMapper(ProductRatingEntityMapper.class);

    public abstract ProductRating map(ProductRatingEntity productRatingEntity);

    public abstract ProductRatingEntity map(ProductRatingInput productRatingInput);
}
