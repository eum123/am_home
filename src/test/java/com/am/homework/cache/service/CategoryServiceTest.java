package com.am.homework.cache.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService service;
	
	@Test
	public void test() {
		System.out.println(service.getCategoryList());
	}
}
