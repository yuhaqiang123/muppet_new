package com.muppet.data.util;

import com.muppet.data.core.InitException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CloseUtil {

	public static boolean close(ResultSet rs){
		if(rs==null){
			return true;
		} else
			try {
				if(!rs.isClosed()){
					rs.close();
					return true;
				}
				else{
					return true;
				}
			} catch (SQLException e) {
				throw new InitException(e);
			}
	}
	
	public static boolean close(Connection conn){
		if(conn==null){
			return true;
		} else
			try {
				if(!conn.isClosed()){
					conn.close();
				}
				return true;
			} catch (SQLException e) {
				
				e.printStackTrace();
				return false;
			}
	}
	
	public static boolean close(PreparedStatement stmt){
		if(stmt==null){
			return true;
		} else
			try {
				stmt.close();
				return true;
			} catch (SQLException e) {
				
				return false;
			}
	}
	
	
}
