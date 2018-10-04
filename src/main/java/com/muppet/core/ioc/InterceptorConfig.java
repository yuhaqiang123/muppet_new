package com.muppet.core.ioc;

import com.muppet.core.ioc.annotation.After;
import com.muppet.core.ioc.annotation.Around;
import com.muppet.core.ioc.annotation.Before;
import com.muppet.core.ioc.annotation.Interceptor;
import com.muppet.data.util.Utils;

import java.lang.reflect.Method;

public class InterceptorConfig {

	
	private ApplicationContext applicationContext;
	public InterceptorConfig(ApplicationContext  applicationContext){
		this.applicationContext = applicationContext;
	}
	
	public void config(Class clazz){
		Interceptor interceptor = (Interceptor)clazz.getAnnotation(Interceptor.class);
		if(Utils.notEmpty(interceptor)){
			Method[] methods = clazz.getDeclaredMethods();
			if(Utils.empty(methods)){
				return;
			}
			for(Method method:methods){
				Around around = method.getDeclaredAnnotation(Around.class);
				Before before = method.getDeclaredAnnotation(Before.class);
				After after = method.getDeclaredAnnotation(After.class);
			}
		}else{
			
		}
	}
	
	private void interceptorMethodAnnotationCheck(Method method){
		
	}
	
	
	
}
