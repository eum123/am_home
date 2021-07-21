package com.am.homework.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;

import com.am.homework.cache.entity.CategoryEntity;
import com.am.homework.cache.entity.ProductEntity;
import com.am.homework.cache.model.Product;
import com.am.homework.cache.repository.CategoryRepository;
import com.am.homework.cache.repository.ProductRepository;
import com.am.homework.common.ExternalInvokeException;

@SpringBootTest
@Sql(scripts = {"file:src/main/resources/product.sql", "file:src/main/resources/category.sql"})
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class AdminServiceTest {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AdminService service;
	
	@Test
	void updateCategoryTest() throws ExternalInvokeException {
		
		CategoryEntity entity = categoryRepository.findById(1).get();
		
		service.updateCategoryName(1, "스킨케어_1");
		
		CategoryEntity newEntity = categoryRepository.findById(1).get();
		
		assertNotEquals(entity.getCategoryName(), newEntity.getCategoryName());
	}
	
	@Test
	void removeProductTest( ) throws ExternalInvokeException {
		
		int totalCount = (int)productRepository.count();
		
		Product removedProduct = service.removeProduct(2);
		
		assertEquals(productRepository.count(), totalCount -1);
		
		ProductEntity entity = productRepository.findById(2L).orElse(null);
		assertNull(entity);
	}
	
	@Test
	void updateProductPriceTest( ) throws ExternalInvokeException {
		
		ProductEntity entity = productRepository.findById(2L).orElse(null);
		
		Product p = Product.builder().brandName("brandName1")
				.productNo(2)
				.categoryNo(1)
				.productName("productName1")
				.productName(null)
				.productPrice(BigDecimal.valueOf(10))
				.build();
		
		Product removedProduct = service.updateProduct(p.getProductNo(), p);
		
		ProductEntity newEntity = productRepository.findById(2L).orElse(null);
		
		assertNotEquals(entity.getProductPrice().intValue(), newEntity.getProductPrice().intValue());
		assertEquals(newEntity.getProductPrice().intValue(), p.getProductPrice().intValue());
	}
	
	@Test
	void updateProductNameTest( ) throws ExternalInvokeException {
		
		ProductEntity entity = productRepository.findById(2L).orElse(null);
		
		Product p = Product.builder().brandName("brandName1")
				.productNo(2)
				.categoryNo(1)
				.productName("productName1")
				.productName("productName1")
				.productPrice(null)
				.build();
		
		Product removedProduct = service.updateProduct(p.getProductNo(), p);
		
		ProductEntity newEntity = productRepository.findById(2L).orElse(null);
		
		assertNotEquals(entity.getProductName(), newEntity.getProductName());
		assertEquals(newEntity.getProductName(), p.getProductName());
	}
}
