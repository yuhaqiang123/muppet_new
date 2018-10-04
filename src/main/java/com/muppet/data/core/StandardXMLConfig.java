package com.muppet.data.core;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.data.datasource.DataSourceListener;

public class StandardXMLConfig extends AbstractConfig implements XMLConfig, DataSourceXMLConfig{

	private StandardDataSourceXMLConfig config;
	
	private DataSourceManager dataSourceManager;
	
	private DataSourceResource[] dataSourceResources;
	
	private DataSourceListener dataSourceListener;
	
	public StandardXMLConfig(String xmlPath,ApplicationContext applicationContext) {
		super(xmlPath);
		
		config = new StandardDataSourceXMLConfig(getXMLConfigResource());
		dataSourceResources = config.getDataSourceInfo();
		dataSourceListener = config.getDataSourceListener();
	}

	@Override
	public DataSourceResource[] getDataSourceInfo() {
		return dataSourceResources;
	}

	@Override
	public DataSourceListener getDataSourceListener() {
		return dataSourceListener;
	}
	
	
	
	
}
