package com.am.homework.common;

/**
 * cache 변경 오류.
 * @author a28097823
 *
 */
public class RuntimeCacheUpdateException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3998544635732947075L;

	public RuntimeCacheUpdateException(Throwable t) {
		super(t);
	}
}
