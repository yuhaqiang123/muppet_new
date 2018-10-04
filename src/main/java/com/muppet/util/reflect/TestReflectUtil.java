package com.muppet.util.reflect;

import com.muppet.data.util.ArrayUtil;

import java.util.List;

/**
 * Created by yuhaiqiang on 17/2/12.
 */
public class TestReflectUtil {
    public static void testGetClasses(){
        List list = ReflectUtil.getClasses("cn.bronzeware");
        ArrayUtil.println(list);
    }

    public static void main(String[] args){
        TestReflectUtil.testGetClasses();
    }
}
