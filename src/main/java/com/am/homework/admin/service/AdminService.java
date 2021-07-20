package com.am.homework.admin.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.am.homework.cache.entity.CategoryEntity;
import com.am.homework.cache.entity.ProductEntity;
import com.am.homework.cache.repository.CategoryRepository;
import com.am.homework.cache.repository.ProductRepository;
import com.am.homework.cache.util.CategoryHelper;
import com.am.homework.cache.util.ProductHelper;
import com.am.homework.cache.vo.Category;
import com.am.homework.cache.vo.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminService {

	@Autowired
	private ExternalService externalService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional(rollbackFor = Exception.class)
	public Category updateCategoryName(int categoryNo, String categoryName) throws Exception {

		CategoryEntity categoryEntity = categoryRepository.findById(categoryNo).orElse(null);

		if (categoryEntity != null) {
			categoryEntity.setCategoryName(categoryName);
			categoryRepository.save(categoryEntity);
		}

		// sync cache
		externalService.syncCategory(categoryNo);

		log.info("edit category : {}", categoryEntity);
		return CategoryHelper.createByEntity(categoryEntity);
	}

	@Transactional(rollbackFor = Exception.class)
	public Product updateProduct(long productNo, Product product) throws Exception {
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
			externalService.syncProduct(productNo);

			log.info("edit product : {}", productEntity);
		}

		return ProductHelper.createByEntity(productEntity);
	}
}
