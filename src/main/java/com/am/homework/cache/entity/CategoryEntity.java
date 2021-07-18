package com.am.homework.cache.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity {
    @Id
    @JsonIgnore
    @Column(name = "category_no")
    private int categoryNo;

    @JsonProperty
    @Column(name = "category_name")
    private String categoryName;

    @JsonIgnore
    @Column(name = "parent_no")
    private Integer parentNo;

    @JsonIgnore
    @Column
    private int depth;

    @Builder
    public CategoryEntity(int categoryNo, String categoryName, Integer parentNo, int depth) {
        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
        this.parentNo = parentNo;
        this.depth = depth;
    }
}