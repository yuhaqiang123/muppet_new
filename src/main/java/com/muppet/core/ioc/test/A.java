package com.muppet.core.ioc.test;

import com.muppet.core.ioc.annotation.Component;
import com.muppet.core.ioc.annotation.Component.Scope;
import com.muppet.data.util.log.Logger;

/**
 * Created by yuhaiqiang on 17/2/12.
 */
@Component(scope=Scope.singleton)
public class A {
    public A(){
    	//ArrayUtil.println(Thread.currentThread().getStackTrace());
        Logger.debugln("A");
    }
}
