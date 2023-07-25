package com.veekhere.storebase.controller;

import com.veekhere.storebase.domain.model.StoreModel.*;
import com.veekhere.storebase.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @QueryMapping
    public Collection<Store> searchStores() {
        return storeService.searchProducts();
    }
}
