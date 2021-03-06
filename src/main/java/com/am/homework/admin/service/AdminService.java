package com.am.homework.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.am.homework.admin.ExternalCommand;
import com.am.homework.cache.entity.CategoryEntity;
import com.am.homework.cache.entity.ProductEntity;
import com.am.homework.cache.model.Category;
import com.am.homework.cache.model.Product;
import com.am.homework.cache.repository.CategoryRepository;
import com.am.homework.cache.repository.ProductRepository;
import com.am.homework.cache.util.CategoryHelper;
import com.am.homework.cache.util.ProductHelper;
import com.am.homework.common.ExternalInvokeException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

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
		externalService.syncCategory(ExternalCommand.UPDATE, categoryNo);

		log.info("edit category : {}", categoryEntity);
		return CategoryHelper.createByEntity(categoryEntity);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Category removeCategory(int categoryNo) throws ExternalInvokeException {

		CategoryEntity categoryEntity = categoryRepository.findById(categoryNo).orElse(null);

		if (categoryEntity != null) {
			categoryRepository.delete(categoryEntity);
		}

		// sync cache
		externalService.syncCategory(ExternalCommand.UPDATE, categoryNo);

		log.info("edit category : {}", categoryEntity);
		return CategoryHelper.createByEntity(categoryEntity);
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
			externalService.syncProduct(ExternalCommand.UPDATE, productNo);

			log.info("edit product : {}", productEntity);
			
			return ProductHelper.createByEntity(productEntity);
		} else {
			
			log.info("???????????? ??? ??? ??????. : {}", product);
			
			return null;
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Product insertProduct(long productNo, Product product) {
		ProductEntity productEntity = productRepository.findById(productNo).orElse(null);

		if (productEntity == null) {
			//insert
			ProductEntity newEntity = ProductHelper.createByProduct(product);
			productRepository.save(newEntity);
			
			log.info("insert product : {}", productEntity);
			
			return ProductHelper.createByEntity(productEntity);
		} else {
			log.info("?????? ??? ??? ??????. : {}", productEntity);
			
			return null;
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Product removeProduct(long productNo) throws ExternalInvokeException {
		ProductEntity productEntity = productRepository.findById(productNo).orElse(null);

		if (productEntity != null) {
			
			productRepository.delete(productEntity);

			// sync cache
			externalService.syncProduct(ExternalCommand.DELETE, productNo);

			log.info("remove product : {}", productEntity);
			
			return ProductHelper.createByEntity(productEntity);
		}
		
		return null;
		
	}
}
