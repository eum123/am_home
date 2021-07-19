package com.am.homework.cache.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.am.homework.cache.component.vo.Category;
import com.am.homework.cache.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api("카테고리 API")
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * category list 조회.
	 * 
	 * @return
	 */
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Category.class) })
	@ApiOperation(value = "카테고리 리스트 조회")
	@GetMapping(value = "list")
	public ResponseEntity<Map<Integer, Category>> getCategoryList() {
		return new ResponseEntity<Map<Integer, Category>>(categoryService.getCategoryList(), HttpStatus.OK);
	}
}
