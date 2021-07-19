package com.am.homework.cache.component.util;

import com.am.homework.cache.component.vo.Product;
import com.am.homework.cache.entity.ProductEntity;

public class ProductHelper {
	public static Product createByEntity(ProductEntity entity) {

		return Product.builder()
				.brandName(entity.getBrandName())
				.categoryNo(entity.getCategoryNo())
				.productName(entity.getProductName())
				.productNo(entity.getProductNo())
				.build();
	}
}
