package com.kagrana.util;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ReadCSVTest {
	private Log log;

	@Test
	public void test() {
		log = new Log();
		ReadCSV obj = new ReadCSV("csvsample.csv");
		List<HashMap<String, String>> mapList = obj.run();
		log.write("<< Reading retrived values >>");
		for(HashMap<String, String> map : mapList){
			Set<String> keys = map.keySet();
			for(String key : keys)
				log.write("Key : "+key +"\tValue : "+map.get(key));
		}
	}

}
