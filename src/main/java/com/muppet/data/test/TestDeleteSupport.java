package com.muppet.data.test;

import com.muppet.data.core.Session;
import com.muppet.data.entities.Note;
import com.muppet.data.transaction.Transaction;
import com.muppet.data.transaction.TransactionExecute;
import com.muppet.data.util.log.Logger;
import com.muppet.util.testframework.Test;

import java.sql.SQLException;

@Test
public class TestDeleteSupport extends TestSuper{
	
	/**
	 * 
	 * 测试 Session.delete(Class, whereCondition, conditionValue)
	 * 
	 */
	@Test
	public void testDelete(){
		Session session = sessionFactory.getSession();
		
		String deleteContidition = " value = ? ";
		Object[] conditionValue = new Object[]{"2"};
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			Logger.println(session.query(Note.class, deleteContidition, conditionValue));
			
			session.delete(Note.class, deleteContidition, conditionValue);
			
			Logger.println(session.query(Note.class, deleteContidition, conditionValue));
			transaction.rollback();
		}catch(Exception e){
			try {
				transaction.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		session.close();
	}
	
	/**
	 * 测试Session.delete(Class, whereCondition, conditionValue)
	 */
	@Test
	public void testDeleteByPrimaryKeyOnTransactionExecute(){
		sessionFactory.transactionOperationCallbackTest(new TransactionExecute() {
			@Override
			public Object execute(Session session, Transaction transaction) {
				
				String deleteContidition = " value = ? ";
				Object[] conditionValue = new Object[]{"2"};
				Logger.println(session.query(Note.class, deleteContidition, conditionValue));
				
				session.delete(Note.class, deleteContidition, conditionValue);
				
				Logger.println(session.query(Note.class, deleteContidition, conditionValue));
				return null;
			}
		});
	}
	
	/**
	 * 测试Session.deleteByPrimaryKey(Class, primaryKeyValue)
	 */
	@Test
	public void testDeleteByPrimaryKey(){
		sessionFactory.transactionOperationCallbackTest(new TransactionExecute() {
			
			@Override
			public Object execute(Session session, Transaction transaction) {
				Logger.println(session.queryById(Note.class, 2));
				session.deleteByPrimaryKey(Note.class, 2);
				Logger.println(session.queryById(Note.class, 2));
				return null;
			}
		});
	}
	
	public static void main(String[] args){
		TestDeleteSupport s = new TestDeleteSupport();
		s.testDeleteByPrimaryKey();
	}
	

}
