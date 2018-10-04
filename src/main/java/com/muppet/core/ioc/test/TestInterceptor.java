package com.muppet.core.ioc.test;

import com.muppet.core.ioc.annotation.*;
import com.muppet.core.ioc.aop.PointCut;

@Component
@Interceptor
public class TestInterceptor {

	public TestInterceptor() {
		System.out.println("xxxx");
	}
	
	@Before(annotation=TestAop.class)
	public void method(PointCut pointCut){
		System.out.println(pointCut);
	}
	
	@After(annotation=TestAop.class)
	public void hello(PointCut pointCut){
		System.out.println("after");
	}
	
	@Around(annotation = TestAop.class)
	public void around(PointCut pointCut){
		/*MethodProxy method = pointCut.getMethodProxy();
		Object proxy = pointCut.getTargetObject();
		Object[] params = pointCut.getParam();
		try {
			//method.invokeSuper(proxy, params);
		} catch (Throwable e) {
			e.printStackTrace();
		}*/
	}
	
	//@Around(annotation = TestAop.class)
	public void aroundq(PointCut pointCut){
		/*MethodProxy method = pointCut.getMethodProxy();
		Object proxy = pointCut.getTargetObject();
		Object[] params = pointCut.getParam();
		try {
			//method.invokeSuper(proxy, params);
		} catch (Throwable e) {
			e.printStackTrace();
		}*/
	}
	
	
}
