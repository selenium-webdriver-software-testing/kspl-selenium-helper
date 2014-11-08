package com.kagrana.util;

import java.net.MalformedURLException;
import java.net.URL;

public class MiscellaneousFunctions {
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

}
