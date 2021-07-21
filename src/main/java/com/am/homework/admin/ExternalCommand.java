package com.am.homework.admin;

public enum ExternalCommand {
	UPDATE("UPDATE"),INSERT("INSERT"),DELETE("DELTE");
	
	private String code;
	
	private ExternalCommand(String code) {
		this.code = code;
	}
}
