package com.kagrana.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ReadCSV {
	private String fileName;
	private Log log;
	public ReadCSV(String fileName,Log log){
		this.fileName = fileName;
		this.log = log;
	}
	public static void main(String[] args) {
		Log log = new Log();
		ReadCSV obj = new ReadCSV("csvsample.csv",log);
		List<HashMap<String, String>> mapList = obj.run();
		log.write("<< Reading retrived values >>");
		for(HashMap<String, String> map : mapList){
			Set<String> keys = map.keySet();
			for(String key : keys)
				log.write("Key : "+key +"\tValue : "+map.get(key));
		}
	}
	 
	public List<HashMap<String, String>> run() {
	    List<HashMap<String, String>> mapList = new ArrayList<HashMap<String,String>>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 	try {
	 		String[] keys;
	 		br = new BufferedReader(new FileReader(this.fileName));
	 		HashMap<String, String> map = new HashMap<String, String>();
	 		if((line = br.readLine()) != null){
	 			keys = line.split(cvsSplitBy);
	 		}else return null;
	 		
	 		while ((line = br.readLine()) != null) {
	 
				String[] values = line.split(cvsSplitBy);
				for(int i =0; i< keys.length ; i++){
					map.put(keys[i], values[i]);
					log.write("Key : "+ keys[i]+"\tValue : "+values[i]);
				}
				mapList.add(map);
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return mapList;
	  }
}
