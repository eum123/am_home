package com.am.homework.cache.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.am.homework.cache.common.algorithm.LRUCache;
import com.am.homework.cache.vo.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductCache {
	private final Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean isUpdate = false;
	
	private LRUCache<Product> cache = new LRUCache<Product>(1000);
	//카테고리별 product_no
	private Map<Integer, List<Long>> categoryGroup = new HashMap<>();
	
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
	
	public List<Long> getProductNoList(int categoryNo) throws Exception {
		lock.lock(); 
		
		try {
			if(isUpdate) {
				condition.await(1000, TimeUnit.SECONDS);
			}
			
			return categoryGroup.get(categoryNo);
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 상품 정보 update
	 * @param product
	 */
	public void update(Product product) {
		lock.lock(); 

        try {
        	
        	isUpdate = true;
        	
        	log.debug("update Product : {}", product);
        	
        	//상품저장.
        	cache.put(product.getProductNo(), product);
        	
        	//카테고리가 변경되었는지 확인한다.
        	//TODO:categoryGroup에서 상품에 포함되어 있는 categoryNo를 구하여 동일하지 않으면 categoryGroup을 변경한다.
        	
			
        } finally {
        	isUpdate = false;
        	condition.signalAll();
        	lock.unlock();
        }
	}
	
	
	
	/**
	 * 상품 정보 저장.
	 * @param product
	 */
	public void add(Product product) {
		lock.lock(); 

        try {
        	
        	isUpdate = true;
        	
        	log.debug("add Product : {}", product);
        	
        	//상품저장.
        	cache.put(product.getProductNo(), product);
        	
        	//카테고리별 product_no 저장.
        	if(!categoryGroup.containsKey(product.getCategoryNo())) {
        		categoryGroup.put(product.getCategoryNo(), new ArrayList<Long>());
        	} 
        	categoryGroup.get(product.getCategoryNo()).add(product.getProductNo());
			
        } finally {
        	isUpdate = false;
        	condition.signalAll();
        	lock.unlock();
        }
	}
	
	/**
	 * 초기
	 */
	public void reset() {
		
		lock.lock(); 

        try {
        	
        	isUpdate = true;
        	
        	log.debug("reset ProductCache");
        	
        	categoryGroup.clear();
        	cache = new LRUCache<Product>(1000);
			
        } finally {
        	isUpdate = false;
        	condition.signalAll();
        	lock.unlock();
        }
		
	}
}
