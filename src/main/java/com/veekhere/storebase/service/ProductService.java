package com.veekhere.storebase.service;

import com.veekhere.storebase.domain.enums.OperationStatus;
import com.veekhere.storebase.domain.model.OperationResultModel.OperationResult;
import com.veekhere.storebase.domain.model.ProductModel.*;
import com.veekhere.storebase.domain.repository.ProductRepository;
import com.veekhere.storebase.domain.repository.entity.ProductEntity;
import com.veekhere.storebase.domain.repository.mapper.ProductEntityMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Collection<Product> searchProducts(ProductFilter filter) {
        ProductEntityMapper productEntityMapper = ProductEntityMapper.MAPPER;
        List<ProductEntity> allProducts = productRepository.searchProducts(filter);
        return allProducts.stream().map(productEntityMapper::map).toList();
    }

    public Product getProduct(UUID id) {
        return productRepository
                .findById(id)
                .map(ProductEntityMapper.MAPPER::map)
                .orElse(null);
    }

    public OperationResult createProduct(ProductInput productInput) {
        ProductEntityMapper productEntityMapper = ProductEntityMapper.MAPPER;
        ProductEntity entity = productEntityMapper.map(productInput);

        try {
            productRepository.save(entity);
            return new OperationResult(OperationStatus.SUCCESS);
        } catch (Exception e) {
            return new OperationResult(OperationStatus.FAILED);
        }
    }

    public OperationResult updateProduct(ProductInput productInput) {
        ProductEntityMapper productEntityMapper = ProductEntityMapper.MAPPER;
        ProductEntity entity = productEntityMapper.map(productInput);

        try {
            productRepository.save(entity);
            return new OperationResult(OperationStatus.SUCCESS);
        } catch (Exception e) {
            return new OperationResult(OperationStatus.FAILED);
        }
    }

    public OperationResult deleteProduct(UUID id) {

        Optional<ProductEntity> maybeProduct = productRepository.findById(id);

        if (maybeProduct.isEmpty()) {
            return new OperationResult(OperationStatus.FAILED);
        }

        ProductEntity productEntity = maybeProduct.orElseThrow(RuntimeException::new);
        productRepository.delete(productEntity);

        return new OperationResult(OperationStatus.SUCCESS);
    }
}
