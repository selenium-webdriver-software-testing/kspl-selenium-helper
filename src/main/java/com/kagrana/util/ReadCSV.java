package com.kagrana.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadCSV {
	private String fileName;
	
	public ReadCSV(String fileName) {
		this.fileName = fileName;
	}

	public List<HashMap<String, String>> run() {
		List<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			String[] keys;
			br = new BufferedReader(new FileReader(this.fileName));
			HashMap<String, String> map = new HashMap<String, String>();
			if ((line = br.readLine()) != null) {
				keys = line.split(cvsSplitBy);
			} else
				return null;

			while ((line = br.readLine()) != null) {

				String[] values = line.split(cvsSplitBy);
				for (int i = 0; i < keys.length; i++) {
					map.put(keys[i], values[i]);
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
