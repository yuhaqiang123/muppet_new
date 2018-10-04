package com.muppet.core.ioc.aop;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.core.ioc.BeanInitializationException;
import com.muppet.core.ioc.annotation.After;
import com.muppet.core.ioc.annotation.Around;
import com.muppet.core.ioc.annotation.Before;
import com.muppet.core.ioc.annotation.Interceptor;
import com.muppet.data.listener.Event;
import com.muppet.data.listener.EventType;
import com.muppet.data.listener.Listener;
import com.muppet.data.util.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class InterceptorConfigListener implements Listener {

	
	@Override
	public EventType eventType() {
		return EventType.BEAN_CLASS_CONFIG;
	}
	
	private InterceptorManager interceptorManager = null;
	
	public InterceptorManager getInterceptorManager(ApplicationContext applicationContext) {
		if(interceptorManager == null){
			interceptorManager = new InterceptorManager(applicationContext);
			applicationContext.registerBean(InterceptorManager.class, interceptorManager);
		}
		return interceptorManager;
	}
	
	
	@Override
	public void event(EventType type, Event event) {
		if(type.equals(EventType.BEAN_CLASS_CONFIG)){
			ApplicationContext context = event.getContext();
			InterceptorManager interceptorManager = getInterceptorManager(context);
			if(Class.class.isAssignableFrom(event.getEventParam().getClass())){
				Class clazz = (Class)event.getEventParam();
				Interceptor interceptor = (Interceptor)clazz.getAnnotation(Interceptor.class);
				if(Utils.notEmpty(interceptor)){
					Method[] methods = clazz.getDeclaredMethods();
					if(Utils.empty(methods)){
						return;
					}
				
					for(Method method:methods){
						Annotation annotation = annotationCheck(clazz, method);
						if(annotation == null){
							continue;
						}
						//System.out.println("于海强");
						Class targetAnnotationType = getTargetAnnotation(method);
						InterceptorMetaData interceptorMetaData = new InterceptorMetaData();
						interceptorMetaData.setInterceptorClazz(clazz);
						interceptorMetaData.setInterceptorMethod(method);
						interceptorMetaData.setInterceptorObject(context.getBean(clazz));
						interceptorMetaData.setInterceptorType(annotation);
						interceptorMetaData.setTargetAnnotationType(targetAnnotationType);
						//interceptorMetaData.setFullyQualifiedClassNamePrefix(fullyQualifiedClassNamePrefix);
						interceptorManager.addInterceptorMetaData(interceptorMetaData);
					}
				}else{
					
				}
			}
		}
	}

	public Annotation annotationCheck(Class clazz, Method method){
		Around around = method.getDeclaredAnnotation(Around.class);
		Before before = method.getDeclaredAnnotation(Before.class);
		After after = method.getDeclaredAnnotation(After.class);
		int i = 0;
		if(around != null){
			i++;
		}
		if(before != null){
			i++;
		}
		if(after != null){
			i++;
		}
		if(i > 1){
			throw new BeanInitializationException(String.format(
					"bean named %s initialization has error, method:%s has too many interceptor　annotation ", clazz.getName(), method.getName()));
		}
		if(i==1){
			return around != null ? around:(before != null? before:after);	
		}else{
			return null;
		}
	}
	
	public Class getTargetAnnotation(Method method){

		Around around = method.getDeclaredAnnotation(Around.class);
		Before before = method.getDeclaredAnnotation(Before.class);
		After after = method.getDeclaredAnnotation(After.class);
		
		if(before != null){
			return before.annotation();
		}
		if(after != null){
			return after.annotation();
		}
		
		if(around != null){
			return around.annotation();
		}
		return null;
	}
	
}
