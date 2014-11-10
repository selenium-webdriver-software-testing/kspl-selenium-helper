package com.kagrana.base;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.kagrana.DTO.WebDriverConfig;
import com.kagrana.util.Log;

public class xRemoteWebDriverTest extends BaseActions {
	
	private WebDriver driver;
	private WebDriverConfig config;
	private Log log;

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
