package com.muppet.data.test;

import com.muppet.core.ioc.annotation.Component;
import com.muppet.data.core.Criteria;
import com.muppet.data.core.Session;
import com.muppet.data.entities.Note;
import com.muppet.data.transaction.Transaction;
import com.muppet.data.transaction.TransactionExecute;
import com.muppet.data.util.ArrayUtil;
import com.muppet.data.util.log.Logger;
import com.muppet.util.testframework.Test;

import java.util.List;

@Test
@Component
public class TestCriteria extends TestSuper{

	
	@Test
	public  void test1(){

		sessionFactory.transactionOperationCallbackTest(new TransactionExecute() {
			
			@Override
			public Object execute(Session session, Transaction transaction) {
				Criteria<Note> criteria = session.createCriteria(Note.class);
				criteria.andPropGreater("user_id", 0);
				
				Logger.println("查询结果个数： " + criteria.select("value,pk,password,user_id")
						.list().size());
				return null;
			}
		});
	}
	
	@Test
	public void test2(){
		sessionFactory.transactionOperationCallbackTest(new TransactionExecute() {
			
			@Override
			public Object execute(Session session, Transaction transaction) {
				Criteria<Note> criteria = session.createCriteria(Note.class);
				criteria.andPropGreaterEq("pk", 1);
				criteria.andPropLess("value", 5);
				List<Note> notes = criteria.list();
				Logger.println("查询结果:");
				ArrayUtil.println(notes);
				return null;
			}
		});
		
	}
	@Test
	public void test3(){
		sessionFactory.transactionOperationCallbackTest(new TransactionExecute() {
			
			@Override
			public Object execute(Session session, Transaction transaction) {
				Criteria<Note> criteria = session.createCriteria(Note.class);
				criteria.andPropLessEq("username", 7)
						.andPropLike("value", "%6%");
				List<Note> notes = criteria.list();
				ArrayUtil.println(notes);
				return null;
			}
		});
	}
	@Test
	public void test4(){
		sessionFactory.transactionOperationCallbackTest(new TransactionExecute() {
			
			@Override
			public Object execute(Session session, Transaction transaction) {
				Criteria<Note> criteria = session.createCriteria(Note.class);
				criteria.andPropNotEqual("pk", 3);
				List<Note> notes = criteria.list();
				Logger.println("查询结果:");
				ArrayUtil.println(notes);
				return null;
			}
		});
	}
	@Test
	public void test5(){
		sessionFactory.transactionOperationCallbackTest(new TransactionExecute() {
			
			@Override
			public Object execute(Session session, Transaction transaction) {
				Criteria<Note> criteria = session.createCriteria(Note.class);
				criteria.order("pk", false);
				List<Note> notes = criteria.list();
				ArrayUtil.println(notes);
				return null;
			}
		});
	}
	@Test
	public void test6(){
		sessionFactory.transactionOperationCallbackTest(new TransactionExecute() {
			
			@Override
			public Object execute(Session session, Transaction transaction) {
				Criteria<Note> criteria = session.createCriteria(Note.class);
				criteria.andPropLess("pk", 3);
				Criteria<Note> criteria2 = session.createCriteria(Note.class);
				criteria2.andPropGreater("pk", 5);
				criteria.or(criteria2);
				List<Note> notes = criteria.list();
				ArrayUtil.println(notes);
				return null;
			}
		});
	}
	
	
	
	public static void main(String[] args){
		TestCriteria criteria = new TestCriteria();
		criteria.test1();
	}
	
}
