package com.muppet.data.test;

import com.muppet.data.core.Session;
import com.muppet.data.entities.Note;

import java.sql.SQLException;

public class TestMuppet {

//	static SessionFactory factroy = new SessionFactory("data.xml");
	public static Session muppet ;
/*	static
	{
		data = factroy.getSession(true);
	}
*/
	public static void test0(){
		
		Note note = new Note();
		note.setPassword("yuhaiqiang");
		note.setUser_id("34");
		
		note.setUsername("yuhaiqiang1");
		muppet.insert(note);
		//data();
		System.out.println(note.getId());
	}
	
	
	public static void test1(){
		
		Note note = new Note();
		note.setPassword("yuhaiqiang");
		note.setUser_id("34");
		note.setUsername("yuhaiqiang");
		
		muppet.insert(note);
		
		System.out.println(note.getId());
	}
	public static void test2(){
		Note note = new Note();
		note.setId(2);
		note.setPassword("yuhaiqiang");
		note.setUser_id("34");
		note.setValue("value");
		note.setUsername("e");
		System.out.println(muppet.updateByPrimaryKey(note));
	}	
	public static void test3(){
		Note note = new Note();
		note.setId(2);
		note.setPassword("yuhaiqiang");
		note.setUser_id("34");
		note.setValue("value");
		note.setUsername("e");
		//System.out.println(data.deleteByPrimaryKey(note));
	}
	
	public static void test4(){
		Note note = new Note();
		note.setId(2);
		note.setPassword("yuhaiqiang");
		note.setUser_id("34");
		note.setValue("value");
		note.setUsername("e");
		String selectsql = "id = ?";
		System.out.println(
				muppet.query(Note.class, selectsql,  new Object[]{25}).get(0).getUser_id()
				);
		//System.out.println(data.query(selectsql,, note.getClass()));
	}

	public static void test5(){
		
		Note note = new Note();
		note.setId(2);
		note.setPassword("yuhaiqiang");
		note.setUser_id("34");
		note.setValue("value");
		note.setUsername("e");
		String selectsql = "select username from t_note3 where id = ?";
		System.out.println(
				muppet.query(selectsql,new Object[]{3},Note.class).get(0).getId()
				);
	}
	
	
	public static void main(String[] args) throws SQLException{
		/*test1();
		test3();
		test1();*/
		//Transaction transaction = data.beginTransaction();
		test1();
		/*test4();
		
		//transaction.rollback();
		test0();*/
		muppet.close();
		/*test2();
		test3();
		test4();
		Thread thread = new Thread(new  Runnable() {
			
			@Override
			public void run() {
				test3();
				
			}
		});
		thread.start();
		test5();*/
	}

}
