package com.muppet.data.util;

import com.muppet.core.ioc.InitializeException;

public class ExceptionUtil {
	
	/**
	 * 把继承Exception的异常转化为RuntimeException异常，避免编译时检查　
	 * @param e
	 * @return　RuntimeException
	 */
	public static RuntimeException getRuntimeException(Exception e){
		RuntimeException r = new InitializeException(e);
		return r;
	}

	public  static RuntimeException getRuntimeException(){
		return new RuntimeException();
	}
	
	public static RuntimeException getRuntimeException(String msg){
		return new RuntimeException(msg);
	}
	
	
}
