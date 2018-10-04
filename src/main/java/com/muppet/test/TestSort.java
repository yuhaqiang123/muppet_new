package com.muppet.test;

import java.util.*;


public class TestSort {

	
	public static void  main(String[] args){
		
		Scanner in = new Scanner(System.in);
		List<String> list = new ArrayList<String>();
		List<Object> set = new ArrayList<Object>();
		set.add(4);
		set.add(5);
		set.add(6);
		set.add("fe");
		System.out.println(set);
		
		System.out.println(set.contains(4));
		while(in.hasNext()){
			
			String string = in.next();
			list.add(string);
			//System.out.println(string);
			
		}
		
		/*for(String string:list){
			System.out.println(string);
		}*/
		Object[] array = list.toArray();
		Arrays.sort(array,new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				String string1 = (String)o1;
				String string2 = (String)o2;
				return  string1.toUpperCase().compareTo(string2.toUpperCase());
			}
		});
		for(Object string:array){
			System.out.println(string);
		}   
	}
}
