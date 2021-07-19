package com.am.homework.cache.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity {
    @Id
    @Column(name = "category_no")
    private Integer categoryNo;

    @Setter
    @Column(name = "category_name", nullable = true)
    private String categoryName;

    @Column(name = "parent_no", nullable = true)
    private Integer parentNo;

    @Column
    private Integer depth;

    @Builder
    public CategoryEntity(Integer categoryNo, String categoryName, Integer parentNo, Integer depth) {
        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
        this.parentNo = parentNo;
        this.depth = depth;
    }
}