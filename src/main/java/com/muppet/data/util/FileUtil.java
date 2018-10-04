package com.muppet.data.util;

import java.io.*;

public class FileUtil {

	
	
	public static void createFile(File file){
		createFile(file, false);
	}
	
	public static void createFile(File file, boolean dir){
		if(file == null){
			return;
		}
		if(file.getParentFile() != null && !file.getParentFile().exists()){
			createFile(file.getParentFile(), true);
		}else{
			if(!file.exists()){
				try {
					if(dir){
						file.mkdir();
					}else{
						file.createNewFile();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	
	public static String convertFilePathOnOs(String path){
		String osName = System.getProperty("os.name");
		if(osName.startsWith("Windows")){
			path = path.replace("/", File.separator);
		}
		return path;
	}
	
	
	public static void write(byte[] data,String path){
		try {
			FileUtil.createFile(new File(path));
			FileOutputStream out = new FileOutputStream(new File(path));
			out.write(data);
			out.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static byte[] read(String path){
		FileInputStream in;
		try {
			in = new FileInputStream(new File(path));
			byte[] result = new byte[in.available()];
			int s = 0;
			byte[] buffer = new byte[1024];
			int num = -1;
			while((num = in.read(buffer))!=-1){
				System.arraycopy(buffer, 0, result, s, num);
				s+=num;
			}
			return result;
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void main(String[] args) {
		//byte[]  result = FileUtil.read("F://lyyb474871b-a925-4a72-8821-79c8b78fbc85.html");
		//System.out.println(new String(result,"UTF-8"));
		File file = new File("test/a.txt");
		FileUtil.createFile(file);
		/*if(! file.exists()){
			try {
				System.out.println(file.getAbsolutePath());
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
	}
	
	
}
