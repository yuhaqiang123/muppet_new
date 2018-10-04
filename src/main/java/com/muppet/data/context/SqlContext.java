package com.muppet.data.context;

import com.muppet.data.filters.SqlRequest;
import com.muppet.data.filters.SqlResponse;

public interface SqlContext {

	public SqlRequest getSqlRequest();
	
	
	public SqlResponse getSqlResponse();
	
}
