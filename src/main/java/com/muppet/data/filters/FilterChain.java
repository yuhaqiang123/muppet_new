package com.muppet.data.filters;

import com.muppet.data.context.SqlContext;

public interface FilterChain {

	void doChain(SqlContext context);
}
