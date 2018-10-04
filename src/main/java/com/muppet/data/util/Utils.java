package com.muppet.data.util;

import java.io.File;

/**
 * Created by zhangtao on 17/2/19.
 */
public class Utils {

	
	public static void assertNotEmpty(Object object, String msg){
		assert object != null : msg;
	}
	
    public static boolean empty(Object object){
        if(object == null){
            return true;
        }
        return false;
    }

    public static  boolean notEmpty(Object object){
        return !empty(object);
    }


    public static boolean empty(String string){
        if(string == null){
            return true;
        }else{
            if(string.equals("") == true){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public static boolean notEmpty(String string){
        return !empty(string);
    }
    
    
    public static String pkgNameToDirName(String pkgName){
    	
    	return pkgName.replace(".", File.separator);
    }

}
