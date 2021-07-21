package com.am.homework.cache.common;

public enum CategoryDepth {
	FIRST(1),
	SECOND(2);
	
	private int code;
	
	private CategoryDepth(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
