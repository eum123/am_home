package com.am.homework.cache.component.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
public class Product {
	@JsonProperty
    private long productNo;
	
	@JsonProperty
    private String productName;

	@JsonProperty
    private String brandName;
	
	@JsonProperty
    private BigDecimal productPrice;
	
	@JsonProperty
    private Integer categoryNo;

    @Builder
    public Product(long productNo, String productName, String brandName, BigDecimal productPrice, Integer categoryNo) {
        this.productNo = productNo;
        this.productName = productName;
        this.brandName = brandName;
        this.productPrice = productPrice;
        this.categoryNo = categoryNo;
    }
}