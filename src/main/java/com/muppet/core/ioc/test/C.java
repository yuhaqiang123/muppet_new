package com.muppet.core.ioc.test;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.core.ioc.ApplicationContextAware;
import com.muppet.core.ioc.annotation.Component;
import com.muppet.data.util.log.Logger;

/**
 * Created by yuhaiqiang on 17/2/12.
 */
@TestAop
@Component(name="C")
public class C implements ApplicationContextAware {
	static int i = 0;
    public C(A a){
        Logger.println("C"+ ++i);
    }
    public void setApplicationContext(ApplicationContext app){
        B b = app.getBean(B.class);
        Logger.debugln(b);
    }
    
    public void test(){
    	System.out.println("test aop");
    }
}
