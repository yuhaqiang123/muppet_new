package com.muppet.data.test;

import com.muppet.data.core.Session;
import com.muppet.data.entities.Note;
import com.muppet.data.transaction.Transaction;
import com.muppet.data.transaction.TransactionExecute;
import com.muppet.data.util.log.Logger;
import com.muppet.util.testframework.Test;

@Test
public class TestUpdateSupport extends TestSuper{

	@Test
	public void testUpdate(){
		sessionFactory.transactionOperationCallbackTest(new TransactionExecute() {
			
			@Override
			public Object execute(Session session, Transaction transaction) {
				Note note = session.query("select * from t_note where pk = ?", new Object[]{2}, Note.class).get(0);
				Logger.println("查询前记录");
				Logger.println(note);
				note.setUser_id("修改");
				session.update(note, " pk = ?", new Object[]{2});
				
				note = session.query("select * from t_note where pk = ?", new Object[]{2}, Note.class).get(0);
				
				Logger.println(note);
				return null;
			}
		});
	}
	
	@Test
	public void testUpdatePriamryKey(){
		sessionFactory.transactionOperationCallbackTest(new TransactionExecute() {
			
			@Override
			public Object execute(Session session, Transaction transaction) {
				
				Note note = session.queryById(Note.class, 2);
				Logger.println("查询前结果" + note);
				note.setUsername("修改username");
				note.setId(90);
				
				Logger.println("按主键更新：" + session.updateByPrimaryKey(note));
				
				note = session.queryById(Note.class, 2);
				Logger.println("修改后结果" + note);
				return null;
			}
		});
	}
	
	
	public static void main(String[] args){
		TestUpdateSupport test = new TestUpdateSupport();
		test.testUpdatePriamryKey();
	}
	
	
}
