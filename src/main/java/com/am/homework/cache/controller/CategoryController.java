package com.am.homework.cache.controller;

import com.am.homework.cache.model.Category;
import com.am.homework.cache.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api("카테고리 API")
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;

	/**
	 * category list 조회.
	 * 
	 * @return
	 */
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Category.class) })
	@ApiOperation(value = "카테고리 리스트 조회")
	@GetMapping(value = "list")
	public ResponseEntity<Map<Integer, Category>> getCategoryList() {
		return new ResponseEntity<>(categoryService.getCategoryList(), HttpStatus.OK);
	}
	
	/**
	 * category update.
	 * 
	 * @return
	 */
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Category.class) })
	@ApiOperation(value = "카테고리  update")
	@PatchMapping(value = "/{}")
	public ResponseEntity<Category> categoryUpdate(@PathVariable("categoryNo") int categoryNo) {
		return new ResponseEntity<>(categoryService.updateCategory(categoryNo), HttpStatus.OK);
	}
}
