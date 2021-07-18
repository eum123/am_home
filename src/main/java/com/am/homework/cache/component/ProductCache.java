package com.am.homework.cache.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.am.homework.cache.component.algorithm.LRUCache;
import com.am.homework.cache.entity.ProductEntity;
import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductCache {
	private final Lock lock = new ReentrantLock();
	private LRUCache<ProductEntity> cache = new LRUCache<ProductEntity>(1000);
	
	//카테고리별 상품 목록을 구하기 위함.
	private Map<Integer, Set<Long>> categoryGroup = new HashMap();
	
	
	private void set(ProductEntity entity) {
		lock.lock(); // 어드민과 schedule에서 동시에 호출될 수 있어 동기화를 위해 사용한다.

        try {
        	
        	cache.put(entity.getProductNo(), entity);

			Set<Long> group;
			if (!categoryGroup.containsKey(entity.getCategoryNo())) {
				group = new TreeSet<Long>();
				categoryGroup.put(entity.getCategoryNo(), group);
			}
			group = categoryGroup.get(entity.getCategoryNo());
			group.add(entity.getProductNo());
        } finally {
        	lock.unlock();
        }
	}
}
