package com.am.homework.common;

public class CacheException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8592216860458147035L;

	public CacheException(Throwable t) {
		super(t);
	}
	
	public CacheException(String message) {
		super(message);
	} 
}
