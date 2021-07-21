package com.am.homework.cache.common.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * LRUCache
 *
 * 정책 : 가장 오랫동안 사용하지 않는 데이터를 삭제.
 * 선택이유 : DB를 중심으로 별도 관리시스템 통한 데이터 변경을 예측하기 불가능하므로 빈번하게 사용되는 데이터는 바로 업데이트 가능하므로 
 * 			효율적인 메모리 관리를 위해 LRU 알고리즘이 최선의 알고리즘이라고 생각합니다.
 *          
 */

public class LRUCache<T> {

	
	private Map<Long, ListNode<T>> nodeMap;

	private int capacity;

	private ListNode<T> head;

	private ListNode<T> tail;
	
	public LRUCache(int capacity) {
		this.nodeMap = new HashMap<>();
		this.capacity = capacity;
		head = new ListNode(0, 0);
		tail = new ListNode(0, 0);
		head.next = tail;
		tail.prev = head;
	}

	private void remove(ListNode<T> node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		nodeMap.remove(node.key);
		
	}

	private void insertToHead(ListNode<T> node) {
		this.head.next.prev = node;
		node.next = this.head.next;
		node.prev = this.head;
		this.head.next = node;
		nodeMap.put(node.key, node);
	}
	
	public boolean containKey(long key) {
		return nodeMap.containsKey(key);
	}
	
	public int size() {
		return nodeMap.size();
	}

	public T get(long key) {
		if (!nodeMap.containsKey(key)) {
			return null;
		}
		
		//데이터에 존재하는 키 호출 시, 가장 최근 사용 데이터로 (head) 옮기기 위해 기존 공간 remove
		ListNode<T> node = nodeMap.get(key); 
		remove(node);
		insertToHead(node);
		return node.val;
	}

	public void put(long key, T value) {
		ListNode<T> newNode = new ListNode<>(key, value);
		if (nodeMap.containsKey(key)) { 
			//메모리에 존재하는 키 수정 시, 가장 최근 사용 데이터로 (head) 옮기기 위해 기존 공간 remove
			ListNode<T> oldNode = nodeMap.get(key);
			remove(oldNode);
		} else {
			if (nodeMap.size() >= capacity) { 
				//메모리 제한에 도달 시, 최근에 가장 덜 사용된 키의 공간(tail) remove
				ListNode<T> tailNode = tail.prev;
				remove(tailNode);
			}
		}
		insertToHead(newNode);
	}

	private class ListNode<T> {
		private long key;
		private T val;
		private ListNode<T> prev;
		private ListNode<T> next;

		public ListNode(long key, T val) {
			this.key = key;
			this.val = val;
			this.prev = null;
			this.next = null;
		}
	}
}
