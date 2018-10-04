package com.muppet.data.context;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.data.core.ResourceContext;
import com.muppet.data.resource.Container;
import com.muppet.data.resource.ResourceInfo;
import com.muppet.util.reflect.ReflectUtil;

import java.lang.reflect.InvocationHandler;

/**
 * 
 * 获取具体操作数据库操作的Context上下文
 * 
 * @author yuhaiqiang  yuhaiqiangvip@sina.com
 * @time 2017年5月3日 下午5:22:21
 */
public class ContextFactory {

	
	private ResourceContext context;
	private  Container<String, ResourceInfo> container;
	private SelectContext selectContext;
	private UpdateContext updateContext;
	private InsertContext insertContext;
	private DeleteContext deleteContext;
	private ApplicationContext applicationContext;
	
	private InvocationHandler handler = new ContextInvocationHandler();
	
	public ContextFactory(ResourceContext context, ApplicationContext applicationContext){
		this.context = context;
		this.applicationContext = applicationContext;
		this.container = context.getContainer();
		
		selectContext = new SelectContext(container, applicationContext);
		updateContext = new UpdateContext(container, applicationContext);
		insertContext = new InsertContext(container, applicationContext);
		deleteContext = new DeleteContext(container, applicationContext);
		
		/*
		selectContext = proxy(selectContext);
		updateContext = proxy(updateContext);
		insertContext = proxy(insertContext);
		deleteContext = proxy(deleteContext);*/
		
	}
	
	private <T> T proxy(T t){
		return ReflectUtil.getClassProxy(t, handler,new Class[]{Container.class},new Object[]{container});
	}
	
	
	
	public Context getContext(Context.TYPE type){
		switch(type){
		case DELETE_CONTEXT:
			return deleteContext;
		case SELECT_CONTEXT:
			return selectContext;
		case UPDATE_CONTEXT:
			return updateContext;
		case INSERT_CONTEXT:
			return insertContext;
		}
		return null;
	}
	
}
