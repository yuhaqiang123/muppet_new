package com.muppet.data.test;

import com.muppet.data.core.Session;
import com.muppet.data.entities.Note;
import com.muppet.data.transaction.Transaction;
import com.muppet.data.transaction.TransactionExecute;
import com.muppet.data.util.log.Logger;
import com.muppet.util.testframework.Test;

@Test
public class TestInsert extends TestSuper{

	@Test
	public void testInsert(){
		sessionFactory.transactionOperationCallbackTest(new TransactionExecute() {
			
			@Override
			public Object execute(Session session, Transaction transaction) {
				Logger.println("插入前：");
				Logger.println("\t 值：" + session.queryById(Note.class, 10));
				
				Note note = new Note();
				note.setId(10);
				note.setPassword(String.valueOf(10));
				note.setUser_id(String.valueOf(10));
				note.setUsername(String.valueOf(10));
				note.setValue(String.valueOf(10));
				session.insert(note);
				Logger.println(session.queryById(Note.class, 10));
				return null;
			}
		});
	}
	
	public static void main(String[] args){
		TestInsert test = new TestInsert();
		test.testInsert();
	}
	
}
