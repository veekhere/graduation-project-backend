package com.veekhere.storebase.service;

import com.veekhere.storebase.domain.model.StoreModel;
import com.veekhere.storebase.domain.repository.StoreRepository;
import com.veekhere.storebase.domain.repository.entity.StoreEntity;
import com.veekhere.storebase.domain.repository.mapper.StoreEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public Collection<StoreModel.Store> searchProducts() {
        StoreEntityMapper storeEntityMapper = StoreEntityMapper.MAPPER;
        List<StoreEntity> allProducts = storeRepository.findAll();
        return allProducts.stream().map(storeEntityMapper::map).toList();
    }
}
