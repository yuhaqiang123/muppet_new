package com.muppet.data.listener;

import com.muppet.core.ioc.ApplicationContext;

public class Event {

	private ApplicationContext applicationContext;
	
	private Object object = null;
	
	
	public  Event(ApplicationContext applicationContext, Object object){
		this.applicationContext = applicationContext;
		this.object = object;
	}
	
	public Object getEventParam(){
		return object;
	}
	
	
	public ApplicationContext getContext(){
		return applicationContext;
	}
}
