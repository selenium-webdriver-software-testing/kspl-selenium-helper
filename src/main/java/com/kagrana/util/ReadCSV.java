package com.kagrana.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This file helps read data from CSV file.
 * @author mayur
 *
 */

public class ReadCSV {
	private String fileName;
	
	/**
	 * Constructor
	 * @param fileName
	 */
	public ReadCSV(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 
	 * @return this will return list of hashmap
	 */
	public List<HashMap<String, String>> run() {
		List<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			String[] keys;
			br = new BufferedReader(new FileReader(this.fileName));
			HashMap<String, String> map = new HashMap<String, String>();
			// Read first line from CSV file and use it as key
			if ((line = br.readLine()) != null) {
				keys = line.split(cvsSplitBy);
			} else
				return null;
			// Read each line after first line and use it as values
			while ((line = br.readLine()) != null) {
				String[] values = line.split(cvsSplitBy);
				for (int i = 0; i < keys.length; i++) {
					// Store key and values to hash map
					map.put(keys[i], values[i]);
				}
				// add hash map to list
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
