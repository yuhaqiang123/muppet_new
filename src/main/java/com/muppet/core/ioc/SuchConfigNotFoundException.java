package com.muppet.core.ioc;

/**
 * Created by yuhaiqiang on 17/2/12.
 */
public class SuchConfigNotFoundException extends RuntimeException {

    private String msg;
    public SuchConfigNotFoundException(String msg){
        this.msg = msg;
    }


    @Override
    public String getMessage() {
        return msg;
    }
}
