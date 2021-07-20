package com.am.homework.cache.util;

import com.am.homework.cache.entity.ProductEntity;
import com.am.homework.cache.vo.Product;

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
}
