package com.muppet.util.reflect;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public interface ProxyInvocationHandler extends InvocationHandler{

	
	public Object intercept(Object proxy, Method method, Object[] params,
			MethodProxy methodProxy) throws Throwable ;
}
