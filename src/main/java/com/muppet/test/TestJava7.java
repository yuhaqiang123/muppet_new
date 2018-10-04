package com.muppet.test;

public class TestJava7 implements AutoCloseable{

	
	public static void main(String[] args){
		String string = "a,a,a,a,";
		System.out.println(string.split(",")[0]);
	}

	@Override
	public void close() throws Exception {
		
		
	}
}
