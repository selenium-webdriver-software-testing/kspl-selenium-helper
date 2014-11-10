package com.kagrana.base;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.kagrana.DTO.WebDriverConfig;
import com.kagrana.util.Log;

public abstract class BaseActions {
	protected WebDriver driver;
	protected Log log;
	protected WebDriverConfig config;
	protected String baseURL;

	@BeforeTest
	@Parameters({ "remoteURL", "remotePort", "baseURL", "OS", "browser",
			"version", "internal" })
	public void beforeTest(String remoteURL, String remotePort, String baseURL,
			String OS, String browser, String version, String internal)
			throws MalformedURLException, FileNotFoundException {
		config = new WebDriverConfig();
		log = new Log();
		config.setRemoteURL(remoteURL);
		config.setRemotePort(Integer.parseInt(remotePort));
		this.baseURL = baseURL;
		config.setOS(OS);
		config.setBrowserName(browser);
		config.setBrowserVersion(version);
		config.setIntenal(Boolean.parseBoolean(internal));
		driver = xRemoteWebDriver.getInstance(config, log);
		driver.manage().window().maximize();
		driver.get(this.baseURL);
	}

	@AfterTest
	public void afterTest() {
		try {
			driver.close();
		} catch (Exception ignore) {

		}
		try {
			driver.quit();
		} catch (Exception ignore) {

		}
	}

}
