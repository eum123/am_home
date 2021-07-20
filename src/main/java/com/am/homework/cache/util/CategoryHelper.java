package com.am.homework.cache.util;

import com.am.homework.cache.entity.CategoryEntity;
import com.am.homework.cache.vo.Category;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
