package com.am.homework.cache.common;

import lombok.Getter;

@Getter
public enum CategoryDepth {
	FIRST(1),
	SECOND(2);
	
	private int code;
	
	CategoryDepth(int code) {
		this.code = code;
	}

	public boolean equals(int depth) {
		return this.code == depth;
	}
}
