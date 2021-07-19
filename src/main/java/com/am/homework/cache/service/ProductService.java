package com.am.homework.cache.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.homework.cache.component.CacheComponent;
import com.am.homework.cache.component.vo.Product;

@Service
public class ProductService {

	@Autowired
    private CacheComponent component;

    public Product getProduct(long productId) {
//        Optional<ProductEntity> product  = Optional.ofNullable(productCacheComponent.getProduct(productId));
//        return product.orElse(null);
        
        return null;
    }

    public Map<Long, Product> getProductListByCategoryId(int categoryId) {
//        return productCacheComponent.getProductByCategoryId(categoryId);
    	return null;
    }
}