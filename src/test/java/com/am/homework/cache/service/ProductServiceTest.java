package com.am.homework.cache.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.am.homework.cache.entity.ProductEntity;
import com.am.homework.cache.repository.ProductRepository;
import com.am.homework.cache.vo.Product;

@SpringBootTest
@Sql(scripts = {"file:src/main/resources/product.sql"})
public class ProductServiceTest {
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductService service;
	
	
	@Test
	public void getroductByProductIdTest() throws Exception {
		
		Product cache = service.getProductByProductId(1L);
		ProductEntity db = repository.findById(1L).get();
		
		assertEquals(cache.getProductNo(), db.getProductNo());
		assertEquals(cache.getProductName(), db.getProductName());
		assertEquals(cache.getBrandName(), db.getBrandName());
		assertEquals(cache.getCategoryNo(), db.getCategoryNo());
		assertEquals(cache.getProductPrice(), db.getProductPrice());
	}
	
	@Test
	public void getProductListByCategoryIdTest() throws Exception {
		
		List<Product> cacheList = service.getProductListByCategoryId(2);
		List<ProductEntity> dbList = repository.findAllByCategoryNo(2);

		assertEquals(cacheList.size(), dbList.size());
		
		cacheList.forEach(x -> {
			dbList.forEach(y -> {
				if(x.getProductNo() == y.getProductNo()) {
					checkValue(x, y);
					
				}
			});
		});
	}
	
	private void checkValue(Product product, ProductEntity entity) {
		assertEquals(product.getProductNo(), entity.getProductNo());
		assertEquals(product.getProductName(), entity.getProductName());
		assertEquals(product.getBrandName(), entity.getBrandName());
		assertEquals(product.getCategoryNo(), entity.getCategoryNo());
		assertEquals(product.getProductPrice(), entity.getProductPrice());
	}
	
	@Test
	public void resetTest() throws Exception {
		ProductEntity entity = repository.findById(1L).get();
		
		Product preProduct = service.getProductByProductId(1L);
		
		//change name
		entity.setProductName("myname");
		repository.save(entity);
		
		//reset
		service.resetAll();
		
		Product postProduct = service.getProductByProductId(1L);
		
		assertEquals(preProduct.getBrandName(), postProduct.getBrandName());
		
		assertEquals(postProduct.getProductName(), "myname");
		assertEquals(preProduct.getProductNo(), postProduct.getProductNo());
		
	}
	
	
}
