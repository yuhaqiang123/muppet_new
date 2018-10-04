package com.muppet.util.testframework;

import com.muppet.data.util.FileUtil;

import java.io.*;
import java.util.Map;

public class SerializationTestUnitStorage implements TestUnitStorage{

	
	private String path;
	public SerializationTestUnitStorage(String path) {
		this.path = path;
	}
	
	
	@Override
	public Map<String, TestUnitMetaData> resolve() {
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fileInputStream);
			Map<String, TestUnitMetaData> map = (Map<String, TestUnitMetaData>) ois.readObject();
			return map;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Object store(Map<String, TestUnitMetaData> testUnits) {
		File file = new File(path);
		FileUtil.createFile(file);
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
			oos.writeObject(testUnits);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
