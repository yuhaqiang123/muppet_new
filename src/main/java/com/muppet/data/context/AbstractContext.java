package com.muppet.data.context;

import com.muppet.data.filters.StandardFilterChain;
import com.muppet.data.util.ExceptionUtil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public abstract class AbstractContext implements Context{

	private StandardFilterChain standardFilterChain ;
	
	
	
	public AbstractContext(){
		standardFilterChain =  new StandardFilterChain(); 
		
	}
	
	
	public String[] getColumnNames(ResultSet set){
		try{
			ResultSetMetaData data = set.getMetaData();
			int count = data.getColumnCount();
			String[] results = new String[count];
			for(int i = 0;i<count;i++){
				results[i] = data.getColumnName( i + 1);
			}
			return results;
		}catch(SQLException e){
			throw ExceptionUtil.getRuntimeException(e);
		}
	}
}
