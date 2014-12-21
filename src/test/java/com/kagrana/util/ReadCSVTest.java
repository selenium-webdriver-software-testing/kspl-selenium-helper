package com.kagrana.util;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReadCSVTest {
	private Log log;

	@DataProvider(name = "myDataProvider")
	public Object[][] getData() {
		ReadCSV obj = new ReadCSV("csvsample.csv");
		List<HashMap<String, String>> mapList = obj.run();
		return MiscellaneousFunctions.listHashMapToObject(mapList);
	}

	@Test(dataProvider = "myDataProvider")
	public void test(HashMap<String, String> map) {
		log = new Log();
		Set<String> keys = map.keySet();
		for (String key : keys)
			log.write("Key:\t" + key + "\tValue: " + map.get(key));
		for(int i=0;i<5;i++)
			log.write("\n");
	}

}
