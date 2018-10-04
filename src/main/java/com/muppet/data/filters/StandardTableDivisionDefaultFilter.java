package com.muppet.data.filters;

import com.muppet.data.context.DefaultFilter;
import com.muppet.data.context.SqlContext;
import com.muppet.data.tabledivision.TableDivisionStrategy;

public class StandardTableDivisionDefaultFilter implements DefaultFilter {

	private TableDivisionStrategy strategy;
	
	public StandardTableDivisionDefaultFilter(TableDivisionStrategy strategy)
	{
		this.strategy = strategy;
	}
	
	@Override
	public void doFilter(FilterChain chain, SqlContext context) {
		
		
	}

	
}
