package com.muppet.util.testframework;

import com.muppet.core.ioc.ApplicationConfig;
import com.muppet.core.ioc.AutowiredApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFrameworkApplicationContext extends AutowiredApplicationContext {

	private TestAnnotationExecutor testAnnotationExecutor = null;

	
	public TestFrameworkApplicationContext(ApplicationConfig config){
		super(config);
	}
	
	public TestFrameworkApplicationContext(){
		super();
	}
	
	@Override
	protected void beforeInitialize(){
		super.beforeInitialize();
		testAnnotationExecutor = new TestAnnotationExecutor();
		testClasses = new HashMap<>();
	}
	
	@Override
	protected List<Class<?>> scanComponentClass(List<Class<?>> clazzList){
		return testAnnotationExecutor.execute(clazzList);
	}
	
	private  Map<Class, Object> testClasses;
	
	@Override
	protected void beforeRegister(Object key, Object value) {
		super.beforeRegister(key, value);
		if(value == null){
			return;
		}
		Class clazz = value.getClass().getSuperclass();
		if(key instanceof Class){
			clazz = (Class)key;
		}
		
		
		Test test = (Test) clazz.getDeclaredAnnotation(Test.class);
		if(test != null){
			testClasses.put(clazz, value);
		}
	}
	public Map<Class, Object> getTestUnit(){
		return testClasses;
	}
}
