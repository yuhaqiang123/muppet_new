package com.muppet.data.context;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.data.converter.ObjectConvertor;
import com.muppet.data.core.ThreadLocalTransaction;
import com.muppet.data.resource.Container;
import com.muppet.data.resource.ResourceInfo;
import com.muppet.data.sqlgenerate.*;
import com.muppet.data.transaction.Transaction;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Map;
import java.util.Map.Entry;

public class InsertContext  extends AbstractContext{

	
	public InsertContext(Container<String, ResourceInfo> container, ApplicationContext applicationContext){
		this.container = container;
		log = new SqlExecuteLog(applicationContext, SqlExecuteLog.SqlContextLogMode.INSERT);
		this.sqlGenerateHelper  = new SqlGenerateHelper(container, applicationContext);
	}
	private SqlExecuteLog log;
	
	private Container<String, ResourceInfo> container;
	
	private SqlGenerateHelper sqlGenerateHelper;
	
	public Object execute(Object object
			,String wheres 
			,Object[] wherevalues)
	throws ContextException
	{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet results = null;
		Boolean success = false;
		try {
			Transaction transaction = ThreadLocalTransaction.get();
			connection = transaction.getConnection();
			Sql sql = sqlGenerateHelper.execute(object,null,SqlGenerate.INSERT);
			sql.setWhereValues(sql.getValues().values().toArray());
			String sqlString = sql.getSql();
			Map<Field, Object> map = sql.getValues();
			ps = connection.prepareStatement(sqlString,Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			for(Entry<Field, Object> e:map.entrySet()){
				ps.setObject(i,e.getValue());
				
				i++;
			}
			log.log(null, sql);
			int rows = ps.executeUpdate();
			success= (rows== 1 ?true:false);
			results = ps.getGeneratedKeys();
			Object num = null;
			if(results.next())
	          {
	             num = results.getObject(1);
	          }
			
			ObjectConvertor.loadField(sql.getPrimarykey(), object, num);
		} catch (SQLException e) {
			throw new ContextException(e);
		} catch (ParamCanNotBeNullException e) {
			throw new ContextException(e);
		} catch (SqlGenerateException e1) {
			// 
			throw new SqlGenerateContextException(e1);
		}
		finally{
			try {
				if(results!=null){
					results.close();
				}
				
				if(ps!=null){
					ps.close();
				}
				
				/*if(connection!=null){
					connection.close();
				}*/
				
			} catch (SQLException e) {
				// 
				e.printStackTrace();
			}
		}
		return success;
	}
	

}
