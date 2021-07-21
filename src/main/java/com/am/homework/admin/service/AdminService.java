package com.am.homework.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.am.homework.cache.common.ExternalInvokeException;
import com.am.homework.cache.entity.CategoryEntity;
import com.am.homework.cache.entity.ProductEntity;
import com.am.homework.cache.model.Category;
import com.am.homework.cache.model.Product;
import com.am.homework.cache.repository.CategoryRepository;
import com.am.homework.cache.repository.ProductRepository;
import com.am.homework.cache.util.CategoryHelper;
import com.am.homework.cache.util.ProductHelper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

	private static final String UPDATE = "UPDATE";

	private final ExternalService externalService;

	private final CategoryRepository categoryRepository;

	private final ProductRepository productRepository;

	@Transactional(rollbackFor = Exception.class)
	public Category updateCategoryName(int categoryNo, String categoryName) throws ExternalInvokeException {

		CategoryEntity categoryEntity = categoryRepository.findById(categoryNo).orElse(null);

		if (categoryEntity != null) {
			categoryEntity.setCategoryName(categoryName);
			categoryRepository.save(categoryEntity);
		}

		// sync cache
		externalService.syncCategory(UPDATE, categoryNo);

		log.info("edit category : {}", categoryEntity);
		return CategoryHelper.createByEntity(Objects.requireNonNull(categoryEntity));
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Category removeCategory(int categoryNo) throws ExternalInvokeException {

		CategoryEntity categoryEntity = categoryRepository.findById(categoryNo).orElse(null);

		if (categoryEntity != null) {
			categoryRepository.delete(categoryEntity);
		}

		// sync cache
		externalService.syncCategory(UPDATE, categoryNo);

		log.info("edit category : {}", categoryEntity);
		return CategoryHelper.createByEntity(Objects.requireNonNull(categoryEntity));
	}
	

	@Transactional(rollbackFor = Exception.class)
	public Product updateProduct(long productNo, Product product) throws ExternalInvokeException {
		ProductEntity productEntity = productRepository.findById(productNo).orElse(null);

		if (productEntity != null) {
			if (product.getProductName() != null && product.getProductName().trim().length() > 0) {
				productEntity.setProductName(product.getProductName());
			}

			if (product.getProductPrice() != null) {
				productEntity.setProductPrice(product.getProductPrice());

			}
			productRepository.save(productEntity);

			// sync cache
			externalService.syncProduct(UPDATE, productNo);

			log.info("edit product : {}", productEntity);
			
			return ProductHelper.createByEntity(productEntity);
		}
			
		log.info("업데이트 할 수 없음");
		return null;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Product insertProduct(long productNo, Product product) {
		ProductEntity productEntity = productRepository.findById(productNo).orElse(null);

		if (productEntity == null) {
			//insert
			ProductEntity newEntity = ProductHelper.createByProduct(product);
			productRepository.save(newEntity);
			
			log.info("insert product : {}", newEntity);
			return ProductHelper.createByEntity(newEntity);
		}

		log.info("추가 할 수 없음. : {}", productEntity);
		return null;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Product removeProduct(long productNo) throws ExternalInvokeException {
		ProductEntity productEntity = productRepository.findById(productNo).orElse(null);

		if (productEntity != null) {
			
			productRepository.delete(productEntity);

			// sync cache
			externalService.syncProduct("REMOVE", productNo);

			log.info("remove product : {}", productEntity);
			
			return ProductHelper.createByEntity(productEntity);
		}
		
		return null;
		
	}
}
