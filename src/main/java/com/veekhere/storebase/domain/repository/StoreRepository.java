package com.veekhere.storebase.domain.repository;

import com.veekhere.storebase.domain.repository.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {}
