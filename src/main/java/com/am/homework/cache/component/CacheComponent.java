package com.am.homework.cache.component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.am.homework.cache.repository.CategoryRepository;
import com.am.homework.cache.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CacheComponent {
	
	private CategoryCache categoryCache = new CategoryCache();
	private ProductCache productCache = new ProductCache();
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	@PostConstruct
	public void init() {
		// init code goes here
		productRepository.findAll();
	}
	
	@PreDestroy
    public void preDestroy() {
        // ... 생략
    }

}
