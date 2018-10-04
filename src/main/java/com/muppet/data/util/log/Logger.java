package com.muppet.data.util.log;

import com.muppet.data.util.ArrayUtil;
import com.muppet.util.reflect.ReflectUtil;

import java.io.File;

public class Logger {

	public static boolean isStart = true;
	public static boolean isDebug = true;
	public static boolean isError = true;
	public static boolean isInfo = true;
	public static boolean isPrintTime = false;
	
	static{
		String defaultLogFile = ReflectUtil.getClassPath() + "com/muppet/data/util/log/log4j.properties";
		if(new File(defaultLogFile).exists()){
			org.apache.log4j.PropertyConfigurator.configure(defaultLogFile);
		}else{
			
		}
	}
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();
	
	private static StandardLogger standardLogger = new StandardLogger();
	public static void stackPrintln(){
		 standardLogger.println(ArrayUtil.getValues(Thread.currentThread().getStackTrace(), ","));
	}
	
	
	public static void  println(Object object){
		standardLogger.println(object);
	}
	
	
	public static void print(Object message){
		logger.info(message);
	}
	
	public static void error(Object message){
		logger.info(message);
	}
	
	public static void errorln(Object message){
		logger.error(message);
	}
	
	public static void info(Object message){
		logger.info(message);
	}
	
	public static void infoln(Object message){
		logger.info(message);
	}
	
	public static void debug(Object message){
		logger.debug(message);
	}
	
	public static void debugln(Object message){
		logger.debug(message);
	}
	
	
	
	
}
