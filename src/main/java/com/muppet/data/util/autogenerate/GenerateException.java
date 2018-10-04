package com.muppet.data.util.autogenerate;

class GenerateException extends RuntimeException{

	public GenerateException(Throwable throwable){
		super(throwable);
	}
	 
	public GenerateException(String message){
		this.message = message;
	}
	private String message;
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
