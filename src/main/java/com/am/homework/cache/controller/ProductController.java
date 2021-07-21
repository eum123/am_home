package com.am.homework.cache.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.am.homework.cache.model.Product;
import com.am.homework.cache.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@Api("Product API")
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

	private final ProductService service;

	/**
	 * 상품 조회
	 * 
	 * @param productNo
	 * @return
	 */
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Product.class) })
	@ApiOperation(value = "상품 조회")
	@GetMapping(value = "/{productNo}")
	public ResponseEntity<Product> getProduct(@PathVariable("productNo") long productNo) throws InterruptedException {
		Product product = service.getProductByProductId(productNo);

		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	/**
	 * 특정 카테고리에 속한 상품 전체 조회
	 * 
	 * @param categoryId
	 * @return
	 */
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Product.class) })
	@Nullable
	@ApiOperation(value = "특정 카테고리에 속한 상품 전체 조회")
	@GetMapping(value = "category/{cartegoryNo}")
	public ResponseEntity<List<Product>> getProductList(@PathVariable("cartegoryNo") int categoryNo) throws Exception {

		List<Product> list = service.getProductListByCategoryId(categoryNo);
		if (list == null || list.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Product.class) })
	@ApiOperation(value = "상품 정보를 update.")
	@PatchMapping(value = "update/{productNo}")
	public ResponseEntity<Product> updateProduct(@PathVariable("productNo") long productNo) throws Exception {

		return new ResponseEntity<Product>(service.updateProduct(productNo), HttpStatus.OK);
	}
}
