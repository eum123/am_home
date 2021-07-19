package com.am.homework.cache.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.homework.cache.component.CacheComponent;
import com.am.homework.cache.component.vo.Category;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryService {

    @Autowired
    private CacheComponent component;

    public Map<Integer, Category> getCategoryList() {
    	
    	return component.getCategoryList();
    }
}

