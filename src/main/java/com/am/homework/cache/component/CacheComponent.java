package com.am.homework.cache.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.scheduling.annotation.Scheduled;
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
	
	/**
	 * categoryNo에 해당하는 Product 목록.
	 * @param categoryNo
	 * @return
	 * @throws Exception
	 */
	public List<Product> getProductList(int categoryNo) throws Exception {
		List<Product> list = new ArrayList();
		
		productCache.getProductNoList(categoryNo).forEach(x -> {
			try {
				if(!getProduct(x).isEmpty()) {
					list.add(getProduct(x).get());
				}
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		
		return list;
	}
	
	/**
	 * 상품정보를 구한다.
	 * @param productNo
	 * @return
	 * @throws Exception
	 */
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
				productCache.add(product);
				
				return Optional.of(product);
			}
			
		}
	}
	/**
	 * Admin에서 수정된 정보를 반영하기 위한 method
	 * @param productNo
	 */
	public void updateProduct(long productNo) {
		ProductEntity entity = productRepository.findById(productNo).orElse(null);
		if(entity != null) {
			productCache.update(ProductHelper.createByEntity(entity));
		}
	}
	
	private void updateAllProduct() {
		productRepository.findAll().forEach(t -> {
			this.productCache.add(ProductHelper.createByEntity(t));
		});
	}
	private void updateAllCategoryGroup() {
		categoryRepository.findAll(Sort.by(Order.asc("parentNo").nullsFirst()))
		.forEach(t -> {
			try {
				this.categoryCache.setCache(CategoryHelper.createByEntity(t));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	@Override 
	public void onApplicationEvent(ContextRefreshedEvent event) {
		updateAllProduct();

		updateAllCategoryGroup();
    }
	
	@Scheduled(fixedDelay = 5000)
	public void updateAll() {
		// 테이블에 update 날짜를 추가 하여 변경된 데이터만 갱신한다.
	}

}
