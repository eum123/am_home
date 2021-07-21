package com.am.homework.cache.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.am.homework.cache.model.Product;

class ProductCacheTest {
	
	@Test
	void getProductTest() throws InterruptedException  {
		ProductCache cache = new ProductCache();
		
		for(int i = 0; i <1000; i++) {
			cache.add(Product.builder().productNo(i)
					.brandName("brandName_" + i)
					.categoryNo(i)
					.productName("productName_" + i)
					.productPrice(BigDecimal.ONE)
					.build());
		}
		
		assertEquals(1000, cache.size());
		
		for(int i = 0; i <1000; i++) {
			Product p = cache.getProduct(i);
			
			assertEquals(p.getBrandName(), "brandName_" + i);
			assertEquals(p.getCategoryNo(), i);
			assertEquals(p.getProductName(), "productName_" + i);
			
		}
		
		
		
	}
	
	@Test
	void limitTest() {
		ProductCache cache = new ProductCache();
		
		for(int i = 0; i <1000; i++) {
			cache.add(Product.builder().productNo(i)
					.brandName("brandName_" + i)
					.categoryNo(i)
					.productName("productName_" + i)
					.productPrice(BigDecimal.ONE)
					.build());
		}
		
		assertEquals(1000, cache.size());
		
		cache.add(Product.builder().productNo(2000)
				.brandName("brandName_" + 2000)
				.categoryNo(2000)
				.productName("productName_" + 2000)
				.productPrice(BigDecimal.ONE)
				.build());
		assertEquals(1000, cache.size());
	}

	@Test
	void evictionTest() throws InterruptedException {
		ProductCache cache = new ProductCache();
		
		for(int i = 0; i <1000; i++) {
			cache.add(Product.builder().productNo(i)
					.brandName("brandName_" + i)
					.categoryNo(i)
					.productName("productName_" + i)
					.productPrice(BigDecimal.ONE)
					.build());
		}
		
		assertEquals(1000, cache.size());
		
		for(int start = 0 ;start < 100 ; start ++) {
			for(int i = 0; i < 999; i++) {
				cache.getProduct(i);
			}
		}
		cache.add(Product.builder().productNo(2000)
				.brandName("brandName_" + 2000)
				.categoryNo(2000)
				.productName("productName_" + 2000)
				.productPrice(BigDecimal.ONE)
				.build());
		
		assertNull(cache.getProduct(999));
		
	}
	
	
}
