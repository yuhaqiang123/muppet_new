package com.muppet.data.core;

import com.muppet.data.datasource.DataSourceListener;

public interface DataSourceXMLConfig extends XMLConfig{

	public DataSourceResource[] getDataSourceInfo();
	
	public DataSourceListener getDataSourceListener();
}
