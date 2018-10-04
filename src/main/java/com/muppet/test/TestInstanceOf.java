package com.muppet.test;

import com.muppet.data.resource.ResourceInfo;
import com.muppet.data.resource.TableInfo;

public class TestInstanceOf {

	
	
	public static void main(String[] args){
		//System.out.println();
		ResourceInfo resourceInfo = null;
		if(resourceInfo instanceof TableInfo){
			System.out.println("hihie");
		}
	}
}
