package com.am.homework.cache.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    @JsonIgnore
    @Column(name = "product_no")
    private long productNo;

    @Column(name = "product_name")
    private String productName;

    @JsonIgnore
    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "category_no")
    private Integer categoryNo;

    @Builder
    public ProductEntity(long productNo, String productName, String brandName, BigDecimal productPrice, Integer categoryNo) {
        this.productNo = productNo;
        this.productName = productName;
        this.brandName = brandName;
        this.productPrice = productPrice;
        this.categoryNo = categoryNo;
    }
}

