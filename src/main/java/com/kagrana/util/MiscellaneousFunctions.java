package com.kagrana.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class MiscellaneousFunctions {
	/**
	 * This method helps form URL for webdriver
	 * @param remoteURL
	 * @param port
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL getWebDriverURL(String remoteURL,int port) throws MalformedURLException{
		if(port > 0 && remoteURL != null && !remoteURL.equals("")){
			if(!remoteURL.startsWith("http://")){
				return new URL("http://"+remoteURL + ":"+port+"/wd/hub");
			}
			else
				return new URL(remoteURL + ":"+port+"/wd/hub");
		}
		return null;
	}
	/**
	 * This function is mainly used by TestNG data provider
	 * @param mapList
	 * @param log
	 * @return
	 */
	public static Object[][] listHashMapToObject(List<HashMap<String, String>> mapList){
		Object[][] data = new Object[mapList.size()][1];
		{
			int i=0;
			for(HashMap<String, String> map : mapList)
				data[i++][0] = map;
		}
		return data;
	}
}
