package com.am.homework.cache.component;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.am.homework.cache.repository.CategoryRepository;
import com.am.homework.cache.repository.ProductRepository;

@SpringBootTest
@Sql(scripts = {"file:src/main/resources/category.sql", "file:src/main/resources/product.sql"})
public class CacheComponentTest {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CacheComponent component;
	
	
	@Test
	public void load() throws Exception {
	
		
		assertEquals(component.getCategoryList().size(), 6);
		assertEquals(component.getCategoryList().get(1).getSubCategory().size(), 2);
		assertEquals(component.getCategoryList().get(2).getSubCategory().size(), 2);
		
		assertEquals(component.getProductSize(), 1000);
	}
}
