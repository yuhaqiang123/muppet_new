package com.muppet.data.core;

public class IllealInvokeException extends RuntimeException{
	
	private String msg="";
	
	public IllealInvokeException(String msg){
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}

	
	
	
}
