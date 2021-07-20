package com.am.homework.cache.util;

import com.am.homework.cache.entity.ProductEntity;
import com.am.homework.cache.model.Product;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductHelper {
	public static Product createByEntity(ProductEntity entity) {

		return Product.builder()
				.brandName(entity.getBrandName())
				.categoryNo(entity.getCategoryNo())
				.productName(entity.getProductName())
				.productNo(entity.getProductNo())
				.productPrice(entity.getProductPrice())
				.build();
	}
	
	public static ProductEntity createByProduct(Product product) {

		return ProductEntity.builder()
				.brandName(product.getBrandName())
				.categoryNo(product.getCategoryNo())
				.productName(product.getProductName())
				.productNo(product.getProductNo())
				.productPrice(product.getProductPrice())
				.build();
	}
}
