package com.am.homework.cache.component.util;

import com.am.homework.cache.component.vo.Category;
import com.am.homework.cache.entity.CategoryEntity;

public class CategoryHelper {
	/**
	 * categoryentity를 category 로 변환.
	 * @param entity
	 * @return
	 */
	public static Category createByEntity(CategoryEntity entity) {
		
		return Category.builder().categoryName(entity.getCategoryName())
				.categoryNo(entity.getCategoryNo())
				.depth(entity.getDepth())
				.parentNo(entity.getParentNo())
				.build();
	}
}
