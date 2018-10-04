package com.muppet.test;

import javax.annotation.PostConstruct;


public class TestConstruct {

	
	public static void main(String[] args){
		TestConstruct test = new TestConstruct();
		test.test();
	}
	
	public TestConstruct(){
		System.out.println("构造方法");
	}
	public void test(){
		
	}
	
	@PostConstruct
	public void post(){
		System.out.println("post");
	}
	
	
}
