package com.muppet.data.context;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

class ResultSetUtil {

	
	public String[] getColumnNames(ResultSet set){
		try{
			ResultSetMetaData data = set.getMetaData();
			int count = data.getColumnCount();
			String[] results = new String[count];
			for(int i = 0;i<count;i++){
				results[i] = data.getColumnName(i);
			}
			return results;
		}catch(SQLException e){
			/**
			 * ignore
			 */
			e.printStackTrace();
		}
		throw new RuntimeException("获取结果集元数据出错");
	}
}
