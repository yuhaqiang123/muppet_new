package com.muppet.data.core;

import com.muppet.data.context.SelectContext;
import com.muppet.data.resource.Container;

class CriteriaBuilder<T> extends AbstractCriteria<T>{

	


	public CriteriaBuilder(Container container
			, SelectContext selectContext
			,Class<T> clazz)
			throws RuntimeException {
		super(container, selectContext,clazz);
		
	}
	
	
	
}
