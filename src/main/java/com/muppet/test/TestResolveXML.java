package com.muppet.test;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URLDecoder;

public class TestResolveXML {

	
	public static void  main(String[] args) {
		try
		{
			DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
			Document document;
	        DocumentBuilder builder;
			
			builder = dfactory.newDocumentBuilder();
			String classPath = Thread.class.getResource("/").getPath();
			
			/**
			 * URLDecoder.decode(classPath,"UTF-8")对中文路径进行转码
			 */
			document = builder.parse(new File(URLDecoder.decode(classPath,"UTF-8")+"cn\\bronzeware\\data\\xml\\data.xml"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
