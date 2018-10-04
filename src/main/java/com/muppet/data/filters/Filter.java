package com.muppet.data.filters;

import com.muppet.data.context.SqlContext;

public interface Filter {

	public void doFilter(FilterChain chain, SqlContext context);
}
