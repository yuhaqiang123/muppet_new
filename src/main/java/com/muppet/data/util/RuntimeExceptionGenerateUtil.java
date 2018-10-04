package com.muppet.data.util;

import com.muppet.util.reflect.ReflectUtil;

import java.io.File;
import java.nio.charset.Charset;

public class RuntimeExceptionGenerateUtil {

	public static void getInstance(String className, String packageName, String parentPath){
		String source = IOUtil.getContent(new File(
				String.format("%s%s%s%s%s"
						,ReflectUtil.getClassPath()
						,File.separator
						,RuntimeExceptionGenerateUtil.class.getPackage().getName().replace(".", File.separator)
						,File.separator
						,"RuntimeExceptionTemplateFile"
						)));
		source = source.replace("ClassName", className).replace("PackageName", packageName);
		FileUtil.write(source.getBytes(Charset.defaultCharset()), parentPath + File.separator + className + ".java");
	}
	
	public static void main(String[] args){
		RuntimeExceptionGenerateUtil.getInstance("ExceptionInit"
				, "cn.bronzeware.data.util"
				,"E:/Exce");
	}
}
