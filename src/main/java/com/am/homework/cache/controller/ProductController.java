package com.am.homework.cache.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.am.homework.cache.component.vo.Product;
import com.am.homework.cache.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api("Product API")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    /**
     * 상품 조회
     * @param productNo
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Product.class)
    })
    @ApiOperation(value = "상품 조회")
    @GetMapping(value = "/{productNo}")
    public ResponseEntity<Product> getProduct(@PathVariable("productNo") long productNo) {
        Product product = service.getProduct(productNo);
        
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * 특정 카테고리에 속한 상품 전체 조회
     * @param categoryId
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Product.class)
    })
    @Nullable
    @ApiOperation(value = "특정 카테고리에 속한 상품 전체 조회")
    @GetMapping(value = "category/{cartegoryNo}")
    public ResponseEntity< Map<Long, Product>> getProductList(@PathVariable("cartegoryNo") int categoryId) {
    	
        Map<Long, Product> list = service.getProductListByCategoryId(categoryId);
        if (list == null || list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
