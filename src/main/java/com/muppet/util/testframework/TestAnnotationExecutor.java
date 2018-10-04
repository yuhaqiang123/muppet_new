 package com.muppet.util.testframework;

 import com.muppet.data.util.Utils;

 import java.util.LinkedList;
 import java.util.List;

 public class TestAnnotationExecutor {

	public Test execute(Class clazz) {
		Test test = (Test)clazz.getAnnotation(Test.class);
		if(Utils.notEmpty(test)){
			return test;
		}
		else{
			return null;
		}
	}
	
	public List<Class<?>> execute(List<Class<?>> list) {
		List results = new LinkedList<>();
		for(Class<?> clazz : list){
			if(null != execute(clazz)){
				results.add(clazz);
			}
		}
		return results;
	}
	
	
}
