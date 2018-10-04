package com.muppet.data.context;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.data.sqlgenerate.Sql;
import com.muppet.data.util.ArrayUtil;
import com.muppet.data.util.log.Logger;

public class SqlExecuteLog {

	public enum SqlContextLogMode{
		SELECT,
		INSERT,
		DELETE,
		UPDATE
	};
	
	private boolean print = false;
	
	private SqlContextLogMode mode;
	
	private ApplicationContext applicationContext;
	
	public SqlExecuteLog(ApplicationContext applicationContext, SqlContextLogMode mode){
		this.mode = mode;
		this.applicationContext = applicationContext;
		printOnMode(mode);
	}
	
	private void printOnMode(SqlContextLogMode mode){
		switch(mode){
		case SELECT:
			print = true;
			break;
		case INSERT:
			print = true;
			break;
		case UPDATE:
			print = true;
			break;
		case DELETE:
			print = true;
			break;
		default:
			print = false;
		}
	}
	
	public void log(String msg, Sql sql){
		log(msg, sql.getSql(), sql.getWhereValues());
	}
	
	public void log(String msg, String sqlString, Object[] values ){
		if(print){
			if(msg != null){
				Logger.println(msg);
			}
			Logger.println("\toperate start");
			Logger.println(String.format("\t\t SQL: %s", sqlString == null ? "":sqlString));
			Logger.println(String.format("\t\t 相关值：%s", values == null ? "" : ArrayUtil.getValues(values, ",")));
			Logger.println("\toperation end");
		}
	}
	
	
}
