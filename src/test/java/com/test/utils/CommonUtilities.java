package com.test.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class CommonUtilities {

	
public FileInputStream stream=null;
	
	public Properties loadFile(String filename){
		Properties propertyFile = new Properties();
		String  PropertyFilePath=null;
		System.out.println("File Name "+filename);
		System.out.println("Application properties "+Constants.TEKARCH_PROPERTIES_PATH);
		switch(filename) {
		case "applicationproperties":
			PropertyFilePath =Constants.TEKARCH_PROPERTIES_PATH;
			
			System.out.println("Property File "+PropertyFilePath);
			break;
		case "studentRegistrationProperties":
			//PropertyFilePath =Constants.STUDENT_REGISTRATION_PROPERTIES_PATH;
							break;
		}
		try {
			System.out.println(PropertyFilePath);
			stream=new FileInputStream(PropertyFilePath);
			propertyFile.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertyFile;
	}
	
	public String getApplicationProperty(String Key,Properties propertyFile) {
		String value = propertyFile.getProperty(Key);
		System.out.println("Property we got from the file is::" + value);
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	public HashMap getAllPropertiesAsSet(Properties propertyFile) {
		
		return new HashMap(propertyFile);
	}

}
