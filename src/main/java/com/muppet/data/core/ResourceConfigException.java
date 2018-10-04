package com.muppet.data.core;
public class ResourceConfigException extends InitException{

	public static void main(String[] args) {

	}

	private String message = "";
	
	public ResourceConfigException(String message){
		this.message = message;
	}
	
	public ResourceConfigException(){
		
	}
	
	
	
	@Override
	public String message() {
		
		return "解析配置文件出错->"+message;
	}

}
