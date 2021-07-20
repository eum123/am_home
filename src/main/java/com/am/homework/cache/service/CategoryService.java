package com.am.homework.cache.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.am.homework.cache.repository.CategoryRepository;
import com.am.homework.cache.util.CategoryHelper;
import com.am.homework.cache.vo.Category;

@Service
public class CategoryService implements ApplicationListener<ContextRefreshedEvent> {

	private CategoryCache categoryCache = new CategoryCache();
	
	@Autowired
	private CategoryRepository categoryRepository;
	
    /**
     * category 전체 목록.
     * @return
     */
    public Map<Integer, Category> getCategoryList() {
    	return categoryCache.getCache();
    }
    
    /**
     * category를 업데이트 한다.
     * cache에 특정 category만 변경한다.
     * @param categoryNo
     * @return
     */
    public Category updateCategory(int categoryNo) {
    	return null;
    }
    
    @Override 
	public void onApplicationEvent(ContextRefreshedEvent event) {
		updateAllCategory();
    }
    
    private void updateAllCategory() {
		categoryRepository.findAll(Sort.by(Order.asc("parentNo").nullsFirst()))
		.forEach(t -> {
			try {
				this.categoryCache.setCache(CategoryHelper.createByEntity(t));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}
    
	@Scheduled(fixedDelay = 5000)
	public void resetAll() {
		// 테이블에 update 날짜를 추가 하여 변경된 데이터만 갱신한다.
		//아래는 전체 변경으로 적용함.
		
		categoryCache.reset();
		updateAllCategory();
	}
}

