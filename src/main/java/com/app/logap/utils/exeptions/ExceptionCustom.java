package com.app.logap.utils.exeptions;

public class ExceptionCustom extends RuntimeException {

	private static final long serialVersionUID = 3009922603945539909L;
	
	public ExceptionCustom(String msg) {
		super(msg);
	}
	
	public ExceptionCustom(String msg, Throwable cause) {
		super(msg, cause);
	}
}
