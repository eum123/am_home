package com.am.homework.cache.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.am.homework.cache.entity.CategoryEntity;
import com.am.homework.cache.model.Category;
import com.am.homework.cache.service.CategoryCache;

public class CategoryCacheTest {
	@Test
	public void test() throws Exception {
		CategoryCache cache = new CategoryCache();
		
		cache.setCache(Category.builder().categoryName("categoryName1")
				.categoryNo(1)
				.depth(1)
				.parentNo(null)
				.build());
		
		assertEquals(cache.getCache().size(), 1);
		
	}
	
	@Test
	public void saveSub() throws Exception {
		CategoryCache cache = new CategoryCache();
		
		cache.setCache(Category.builder().categoryName("categoryName1")
				.categoryNo(1)
				.depth(1)
				.parentNo(null)
				.build());
		cache.setCache(Category.builder().categoryName("categoryName2")
				.categoryNo(2)
				.depth(1)
				.parentNo(null)
				.build());
		
		cache.setCache(Category.builder().categoryName("categorySubName1")
				.categoryNo(3)
				.depth(2)
				.parentNo(1)
				.build());
		
		assertEquals(cache.getCache().size(), 2);
		
		assertEquals(cache.getCache().get(1).getSubCategory().size(), 1);
		
	}
}
