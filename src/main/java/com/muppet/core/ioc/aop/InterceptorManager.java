package com.muppet.core.ioc.aop;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.core.ioc.InterceptorManage;
import com.muppet.data.util.Utils;
import com.muppet.util.reflect.ProxyInvocationHandler;
import com.muppet.util.reflect.ReflectUtil;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class InterceptorManager implements InterceptorManage {

	
	private ApplicationContext applicationContext;
	public InterceptorManager(ApplicationContext applicationContext){
		this.applicationContext = applicationContext;
		applicationContext.registerBean(InterceptorManager.class, this);
		defaultInvocationHandler = new StandardBeanInterceptorInvocationHandler(applicationContext);
	}
	
	private List<InterceptorMetaData> interceptors = new ArrayList<>();
	
	public void addInterceptorMetaData(InterceptorMetaData interceptorMetaData){
		interceptors.add(interceptorMetaData);
	}
	
	
	private List<InterceptorMetaData> interceptorByAnnotation(Annotation annotation){
		List<InterceptorMetaData> list = new ArrayList<>();
		for(InterceptorMetaData interceptorMetaData: interceptors){
			if(interceptorMetaData.getTargetAnnotationType() != null
					&& interceptorMetaData.getTargetAnnotationType().equals(annotation.annotationType())){
				list.add(interceptorMetaData);
			}
		}
		return list;
	}
	
	private ProxyInvocationHandler defaultInvocationHandler;
	
	@Override
	public Object intercept(Class targetClass, Class[] paramClazzs,Object[] params){
		Class clazz = targetClass;
		Annotation[] annotations = clazz.getDeclaredAnnotations();
		if(Utils.notEmpty(annotations)){
			for(Annotation annotation:annotations){
				List<InterceptorMetaData> list = interceptorByAnnotation(annotation);
				//ArrayUtil.println(list);
				if(list !=null && list.size()> 0){
					ProxyInvocationHandler invocationHandler = new InterceptorInvocationHandler(applicationContext, list);
					return ReflectUtil.getClassProxy(clazz, invocationHandler, paramClazzs, params);
				}
			}
		}else{
			
		}
		return ReflectUtil.getClassProxy(clazz, defaultInvocationHandler, paramClazzs, params);
	}
	
	
	
}
