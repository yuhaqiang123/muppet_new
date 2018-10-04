package com.muppet.data.util;

import java.util.ArrayList;
import java.util.List;

public class TestArray {

	public static void main(String[] args){
		List<String> list= new ArrayList<>();
		list.add("fewa");
		list.add("fewa");
		list.add("fewa");
		list.add("fewa");
		list.toArray();
		//Collections.
		
		//e.
		ArrayUtil.println(list.toArray(new String[list.size()]));
	}
}
