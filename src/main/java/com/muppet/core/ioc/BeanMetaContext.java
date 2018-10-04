package com.muppet.core.ioc;

import com.muppet.data.util.Utils;

import java.util.HashMap;

public class BeanMetaContext {

	private HashMap<String, BeanMeta> beanNameMetas = new HashMap<>(100);
	
	private HashMap<Class, BeanMeta> clazzMetas = new HashMap<>(100);
	
	public BeanMeta getMeta(String beanName){
		return beanNameMetas.get(beanName);
	}
	
	public BeanMeta getMeta(Class clazz){
		return clazzMetas.get(clazz);
	}
	
	public void setMeta(BeanMeta meta){
		if(Utils.notEmpty(meta.getBeanName())){
			
			beanNameMetas.put(meta.getBeanName(), meta);
		}
		
		if( Utils.notEmpty(meta.getClazz())){
			clazzMetas.put(meta.getClazz(), meta);
		}
	} 
	
}
