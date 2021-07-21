package com.am.homework.cache.service;

import java.util.HashMap;
import java.util.Map;

import com.am.homework.cache.common.CategoryDepth;
import com.am.homework.cache.model.Category;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryCache {
	
	
	@Getter
	private Map<Integer, Category> cache = new HashMap<>();


	/**
	 * category 저장 
	 * @param entity
	 */
	public void setCache(Category entity) throws Exception {
		
		log.debug("add category : {}", entity);
		
		if(CategoryDepth.FIRST.equals(entity.getDepth())) {
			//first depth
			if(cache.containsKey(entity.getCategoryNo())) {
				//duplicate
				throw new Exception("데이터 저장 오류 : category_no 가 중복(" + entity.getCategoryNo() + ")");
			} else {
				cache.put(entity.getCategoryNo(), entity);
			}
		} else {
			//second depth
			if(cache.containsKey(entity.getParentNo())) {
				Category category = cache.get(entity.getParentNo());
				
				//sub category의 이름을 변경한다.
				entity.setCategoryName(category.getCategoryName() + "-" + entity.getCategoryName());
				category.add(entity);
			} else {
				//error
				throw new Exception("데이터 저장 오류 : parent_no의 category 가 없어 sub category를 저장할수 없음.");
			}
		}
	}
	
	public void reset() {
		cache.clear();
	}
}
