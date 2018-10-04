package com.muppet.test;

import java.util.Properties;

public class TestMutiSelect {

	public static void main(String[] args) {

		String resultString = "SELECT t_note.id,title,content,summary,create_time,name FROM t_note JOIN t_category ON "
				+ "t_note.Category_Id = t_category.id WHERE user_id = 1 and status = 1";
		
		Properties properties = new Properties();
		properties.setProperty("url",
				"jdbc:mysql://123.56.225.214:3999/diary?Unicode=true&characterEncoding=utf-8");
		properties.setProperty("username", "root");
		properties.setProperty("password", "root");
	/*	Muppet.initial(properties);
		Muppet data = new Muppet();
		List list = data.query(resultString, null, QueryNote.class);
		System.out.println(list);*/
	}

}
