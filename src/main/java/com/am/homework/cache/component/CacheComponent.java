package com.am.homework.cache.component;

import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.am.homework.cache.component.util.CategoryHelper;
import com.am.homework.cache.component.util.ProductHelper;
import com.am.homework.cache.component.vo.Category;
import com.am.homework.cache.component.vo.Product;
import com.am.homework.cache.entity.ProductEntity;
import com.am.homework.cache.repository.CategoryRepository;
import com.am.homework.cache.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("cache")
public class CacheComponent implements ApplicationListener<ContextRefreshedEvent>{

	private CategoryCache categoryCache = new CategoryCache();
	private ProductCache productCache = new ProductCache();

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Map<Integer, Category> getCategoryList() {
		return categoryCache.getCache();
	}
	
	public int getProductSize() {
		return productCache.size();
	}
	
	public Optional<Product> getProduct(long productNo) throws Exception {
		
		if(productCache.containProductNo(productNo)) {
			return Optional.of(productCache.getProduct(productNo));
			
		} else {
			//select db
			Optional<ProductEntity> result = productRepository.findById(productNo);
			
			//update cache
			if(result.isEmpty()) {
				// db에 데이터가 없음.
				return Optional.ofNullable(null);
			} else {
				
				Product product = ProductHelper.createByEntity(result.get());
				
				//update cache
				productCache.set(product);
				
				return Optional.of(product);
			}
			
		}
	}
	
	@Override 
	public void onApplicationEvent(ContextRefreshedEvent event) {
		productRepository.findAll().forEach(t -> {
			this.productCache.set(ProductHelper.createByEntity(t));
		});

		categoryRepository.findAll(Sort.by(Order.asc("parentNo").nullsFirst()))
				.forEach(t -> {
					try {
						this.categoryCache.setCache(CategoryHelper.createByEntity(t));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});
    }

}
