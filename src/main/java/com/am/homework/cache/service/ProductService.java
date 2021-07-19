package com.am.homework.cache.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.homework.cache.component.CacheComponent;
import com.am.homework.cache.component.vo.Product;

@Service
public class ProductService {

	@Autowired
    private CacheComponent component;

    public Product getProductByProductId(long productId) throws Exception {
    	return component.getProduct(productId).orElse(null);
       
    }

    public List<Product> getProductListByCategoryId(int categoryNo) throws Exception {
    	return component.getProductList(categoryNo);
    }
}