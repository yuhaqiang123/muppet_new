package com.muppet.data.filters;

import com.muppet.data.context.SqlContext;

public class FilterChainWrapper implements FilterChain{

	
	private FilterChain filterChain;
	
	public FilterChainWrapper(FilterChain filterChain){
		this.filterChain = filterChain;
	}
	

	
	
	@Override
	public void doChain(SqlContext context) {
		
			filterChain.doChain(context);
		
	}
	
	

}
