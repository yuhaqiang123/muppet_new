package com.muppet.data.core;

public interface BeanFactory {

	public <T> T  getBean(Class<T> clazz) ;
	
	public Object getBean(String beanName);
	
	
}
