package com.kagrana.base;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.junit.Test;

public class xRemoteWebDriverTest extends BaseActions {
	
	@Test
	public void internalURLTest() throws MalformedURLException, FileNotFoundException {
		config.setIntenal(true);
		driver = xRemoteWebDriver.getInstance(config, log);
		driver.manage().window().maximize();
		driver.get("http://www.kagrana.com/klab");
	}
	@Test
	public void externalURLTest() throws MalformedURLException, FileNotFoundException{
		config.setIntenal(false);
		driver = xRemoteWebDriver.getInstance(config, log);
		driver.manage().window().maximize();
		driver.get("http://www.kagrana.com/klab");
	}

}
