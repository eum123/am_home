package com.am.homework.cache.component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.am.homework.cache.component.algorithm.LRUCache;
import com.am.homework.cache.component.vo.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductCache {
	private final Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean isUpdate = false;
	
	private LRUCache<Product> cache = new LRUCache<Product>(1000);
	
	public boolean containProductNo(long productNo) {
		return cache.containKey(productNo);
	}
	
	public int size() {
		return cache.size();
	}
	
	public Product getProduct(long productNo) throws Exception {
		lock.lock(); 
		
		try {
			if(isUpdate) {
				condition.await(1000, TimeUnit.SECONDS);
			}
			
			return cache.get(productNo);
		} finally {
			lock.unlock();
		}
	}
	
	
	/**
	 * 상품 정보 저장.
	 * @param entity
	 */
	public void set(Product entity) {
		lock.lock(); 

        try {
        	
        	isUpdate = true;
        	
        	log.debug("add Product : " + entity);
        	
        	cache.put(entity.getProductNo(), entity);
			
        } finally {
        	condition.signalAll();
        	lock.unlock();
        }
	}
}
