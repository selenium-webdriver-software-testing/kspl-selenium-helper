package com.kagrana.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.kagrana.DTO.IOOperations;

public class PropertyFileManager {
	Properties properties = null;
	Log log;
	public PropertyFileManager(Log log) throws IOException{
		this(log,"config.properties",IOOperations.READ);
	}
	public PropertyFileManager(Log log,String fileName,IOOperations ioOperations) throws IOException{
		this.properties= new Properties();
		File inputFile = new File(fileName);
		if(!inputFile.exists()) throw new IOException("File does not exist");
		log.write(inputFile.getAbsolutePath());
		InputStream is = new FileInputStream(inputFile.getAbsolutePath());
		this.properties.load(is);
		is.close();
	}
	public String getProperty(String propName){
		if(this.properties == null) throw new NullPointerException("Properties is null");
		if(this.log != null)
		log.write("Prop name: " + propName + "\tValue: " +this.properties.getProperty(propName));
		return this.properties.getProperty(propName);
	}
	public void setProperty(String propName, String propValue) throws Exception{
		log.write("Not yet implemented!");
		properties.setProperty(propName, propName);
		throw new Exception("Yet to be implemented"); 
	}
}
