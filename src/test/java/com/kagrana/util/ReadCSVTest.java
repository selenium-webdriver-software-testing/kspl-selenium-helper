package com.kagrana.util;

import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

public class ReadCSVTest {
	private Log log;

	@Test
	public void test() {
		log = new Log();
		ReadCSV obj = new ReadCSV("csvsample.csv");
		Object[][] data = MiscellaneousFunctions.listHashMapToObject(obj.run());
		log.write("<< Reading retrived values >>");
		log.write("Retrived data length:\t"+data.length);
		for(int i=0; i< data.length; i++){
			HashMap<String, String> map = (HashMap<String, String>) data[i][0];
			Set<String> keys = map.keySet();
			for(String key : keys)
				log.write("Key:\t"+key + "\tValue: "+map.get(key));
		}
	}

}
