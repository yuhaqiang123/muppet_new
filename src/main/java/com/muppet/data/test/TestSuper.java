package com.muppet.data.test;

import com.muppet.data.core.Session;
import com.muppet.data.core.SessionFactory;

public class TestSuper {

	protected SessionFactory factory = null;
	protected Session session = null;
	protected static  SessionFactory sessionFactory;
	
	public TestSuper(){
		if(sessionFactory == null){
			factory = new SessionFactory("muppet.xml");
			sessionFactory = factory;
		}
		factory  = sessionFactory;
	}
}
