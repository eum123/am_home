package com.am.homework.cache.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.homework.cache.component.CategoryCache;
import com.am.homework.cache.entity.CategoryEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("CategoryService")
public class CategoryService {

    @Autowired
    private CategoryCache categoryCacheComponent;

    public List<CategoryEntity> getCategoryList() {
       // return categoryCacheComponent.getCategoryList();
    	
    	return null;
    }
}

