package com.muppet.data.context;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.data.context.SqlExecuteLog.SqlContextLogMode;
import com.muppet.data.converter.ObjectConvertor;
import com.muppet.data.core.ThreadLocalTransaction;
import com.muppet.data.resource.Container;
import com.muppet.data.resource.ResourceInfo;
import com.muppet.data.sqlgenerate.*;
import com.muppet.data.transaction.Transaction;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;


public class UpdateContext  extends AbstractContext{

	public UpdateContext(Container<String, ResourceInfo> container, ApplicationContext applicationContext)
	{
		this.container = container;
		this.sqlGenerateHelper = new SqlGenerateHelper(container, applicationContext);
		log = new SqlExecuteLog(applicationContext, SqlContextLogMode.UPDATE);
	}
	
	private Container<String, ResourceInfo> container;
	private SqlGenerateHelper sqlGenerateHelper;
	
	private SqlExecuteLog log = null;
	
	public Object executeByPrimaryKey(Object object){
		Sql sql = new Sql();
		try {
			sql = sqlGenerateHelper.execute(object,sql, SqlGenerate.UPDATE);
			Field primarykeyField = sql.getPrimarykey();
			String primaryKeyName = sql.getPrimaryKeyName();
			String wheres = primaryKeyName + " = ?";
			Object value = ObjectConvertor.getValue(object, primarykeyField);
			return this.execute(object, wheres, new Object[]{value});
				
		} catch (ParamCanNotBeNullException e) {
			throw new ContextException(e);
		} catch (SqlGenerateException e) {
			throw new ContextException(e);
		} catch (SqlGenerateContextException e) {
			throw new ContextException(e);
		}
	}

	
	public Object execute(Object object
			,String wheres 
			,Object[] wherevalues) 
		throws SqlGenerateContextException
		{
			Connection connection = null;
					PreparedStatement ps = null;
				try {
					Transaction transaction = ThreadLocalTransaction.get();
					connection = transaction.getConnection();
					Sql sql = new Sql();
					sql.setWheres(wheres);
					sql.setWhereValues(wherevalues);
					sql = sqlGenerateHelper.execute(object,sql, SqlGenerate.UPDATE);
					
					String sqlString = sql.getSql();
					Map<Field, Object> map = sql.getValues();
					
					ps = connection.prepareStatement(sqlString);
					
					log.log(null, sql);
					
					int i = 1; 
					for(Field field:sql.getObjectkeys()){
						ps.setObject(i, map.get(field));
						i++;
					}
					
					for(Object object2:wherevalues){
						ps.setObject(i, object2);
						i++;
					}
					
					int success = ps.executeUpdate();
					
					return success > 0 ? true:false;
				} catch (SQLException e) {
					throw new ContextException(e);
				} catch (ParamCanNotBeNullException e) {
					throw new ContextException(e);
				} catch (SqlGenerateException e) {
					throw new SqlGenerateContextException(e);
				}finally {
					try {
						if(ps!=null){
							ps.close();
						}
					} catch (SQLException e) {
						throw new ContextException(e);
					}
				}
	}
	

}
