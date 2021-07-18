package com.am.homework.cache.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.homework.cache.component.ProductCache;
import com.am.homework.cache.entity.ProductEntity;

@Service("ProductService")
public class ProductService {

    @Autowired
    private ProductCache productCacheComponent;

    public ProductEntity getProduct(long productId) {
//        Optional<ProductEntity> product  = Optional.ofNullable(productCacheComponent.getProduct(productId));
//        return product.orElse(null);
        
        return null;
    }

    public List<ProductEntity> getProductListByCategoryId(int categoryId) {
//        return productCacheComponent.getProductByCategoryId(categoryId);
    	return null;
    }
}