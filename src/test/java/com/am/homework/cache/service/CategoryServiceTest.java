package com.am.homework.cache.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.am.homework.cache.entity.CategoryEntity;
import com.am.homework.cache.repository.CategoryRepository;
import com.am.homework.cache.vo.Category;

@SpringBootTest
@Sql(scripts = {"file:src/main/resources/category.sql"})
public class CategoryServiceTest {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private CategoryService service;
	
	
	@Test
	public void categoryListCountTest() throws Exception {
		
		assertEquals(service.getCategoryList().size(), repository.countByDepth(1));
		
		List parent1 = repository.findAllByParentNo(1);
		assertEquals(service.getCategoryList().get(1).getSubCategory().size(), parent1.size());
		
		List<CategoryEntity> parent2 = repository.findAllByParentNo(2);
		assertEquals(service.getCategoryList().get(2).getSubCategory().size(), parent2.size());
		
		
	}
	
	@Test
	public void ctegoryContentTest() throws Exception {
		
		List<CategoryEntity> dbList = repository.findAllByParentNo(2);
		Map<Integer, Category> cacheList = service.getCategoryList().get(2).getSubCategory();
		
		CategoryEntity e = repository.findById(2).get();
		
		dbList.forEach(x -> {
			Category c = cacheList.get(x.getCategoryNo());
			
			assertNotNull(c);
			
			assertEquals(e.getCategoryName() + "-" + x.getCategoryName(), c.getCategoryName());
			assertEquals(x.getDepth(), c.getDepth());
			assertEquals(x.getParentNo(), c.getParentNo());
		});
		
		
		
	}
	
}
