package com.am.homework.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.am.homework.admin.service.AdminService;
import com.am.homework.cache.model.Category;
import com.am.homework.cache.model.Product;
import com.am.homework.common.ExternalInvokeException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * 관리용.
 * 
 * @author a28097823
 *
 */
@RestController
@Api("admin API")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService service;

	/**
	 * 카테고리명 변경
	 *
	 * @return Category
	 * @throws Exception
	 */
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@ApiOperation(value = "카테고리명 변경")
	@PatchMapping(value = "/category/{categoryNo}")
	public ResponseEntity<Category> updateCategoryName(@PathVariable("categoryNo") int categoryNo,
			@RequestBody String categoryName) throws ExternalInvokeException {

		Category updatedCategory = service.updateCategoryName(categoryNo, categoryName);
		if (updatedCategory == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}

	/**
	 * 상품 속성 변경
	 *
	 * @return Product
	 * @throws Exception
	 */
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@ApiOperation(value = "상품 변경")
	@PatchMapping(value = "/product/{productNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> updateProduct(@PathVariable("productNo") int productNo, @RequestBody Product product)
			throws ExternalInvokeException {

		Product updatedProduct = service.updateProduct(productNo, product);
		if (updatedProduct == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	/**
	 * 상품삭제.
	 *
	 * @return Product
	 * @throws Exception
	 */
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@ApiOperation(value = "상품 삭제")
	@DeleteMapping(value = "/product/{productNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> removeProduct(@PathVariable("productNo") int productNo)
			throws ExternalInvokeException {

		Product updatedProduct = service.removeProduct(productNo);
		if (updatedProduct == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	/**
	 * 상품추가.
	 *
	 * @return Product
	 * @throws Exception
	 */
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@ApiOperation(value = "상품 추가.")
	@PutMapping(value = "/product/{productNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> insertProduct(@PathVariable("productNo") int productNo,
			@RequestBody Product product) {

		Product updatedProduct = service.insertProduct(productNo, product);
		if (updatedProduct == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

}
