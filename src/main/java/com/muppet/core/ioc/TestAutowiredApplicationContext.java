package com.muppet.core.ioc;

import com.muppet.core.ioc.test.B;
import com.muppet.core.ioc.test.C;
import com.muppet.data.util.log.Logger;

public class TestAutowiredApplicationContext {

	
	public static void test1(){
		ApplicationContext context = new AutowiredApplicationContext();
		//context.getBean(Selector.class);
		context.getBean(ApplicationContext.class);
		ApplicationContextAware applicationContextAware = context.getBean(ApplicationContextAware.class);
		if(applicationContextAware instanceof C){
			Logger.println("ok");
		}
		B b = context.getBean(B.class);
		B b1 = context.getBean(B.class);
		if(b != b1){
			Logger.println("prototype　success");
		}
		Logger.println(b.a);
		if(b.a !=null && b.c!=null && b.applicationContext!=null ){
			Logger.println("autowried ok");
		}
		
	}
	
	public static void main(String[] args){
		test1();
		
	}
	
}
