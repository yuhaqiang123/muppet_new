package com.muppet.data.filters;

import com.muppet.data.context.SqlContext;
import com.muppet.data.core.XMLConfigResource;

public class FilterWrapper extends XMLConfigResource implements Filter  {

	private String name;
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public Filter getFilter() {
		return filter;
	}



	public void setFilter(Filter filter) {
		this.filter = filter;
	}



	private String url;
	
	private Filter filter;
	
	
	
	@Override
	public void doFilter(FilterChain chain, SqlContext context) {
		filter.doFilter(chain, context);
	}

	
	
}
