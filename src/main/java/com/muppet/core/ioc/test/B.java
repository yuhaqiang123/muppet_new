package com.muppet.core.ioc.test;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.core.ioc.annotation.Autowired;
import com.muppet.core.ioc.annotation.Component;
import com.muppet.data.util.log.Logger;

/**
 * Created by yuhaiqiang on 17/2/12.
 */
@Component(name = "B",scope=Component.Scope.singleton)
public class B {
    public B(A a){
        Logger.println("BBB");
    }
    
    
    @Autowired
    public C c;

    
    @Autowired
    public A a;
    
    
    @Autowired
    public ApplicationContext applicationContext;


}
