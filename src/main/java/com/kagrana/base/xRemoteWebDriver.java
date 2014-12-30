package com.kagrana.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;

import com.kagrana.DTO.WebDriverConfig;
import com.kagrana.util.Log;



public class xRemoteWebDriver extends RemoteWebDriver {
	private Log log;
		
	  
	  public xRemoteWebDriver(CommandExecutor executor, Capabilities desiredCapabilities,Log log)
	  {
	    super(executor, desiredCapabilities);
	    this.log = log;
	  }
	  
	  public xRemoteWebDriver(Capabilities desiredCapabilities,Log log)
	  {
	    super(desiredCapabilities);
	    this.log = log;
	  }
	  
	  public xRemoteWebDriver(URL remoteAddress, Capabilities desiredCapabilities, Log log)
	  {
	    super(remoteAddress, desiredCapabilities);
	    this.log = log;
	  }
	  public static WebDriver getInstance(WebDriverConfig config,Log log) throws MalformedURLException, FileNotFoundException{
		  if(!config.isIntenal()){
			  DesiredCapabilities cap = new DesiredCapabilities();
			  cap.setBrowserName(config.getBrowserName());
			  cap.setPlatform(Platform.valueOf(config.getOS()));
			  cap.setVersion(config.getBrowserVersion());
			  return 
					  new xRemoteWebDriver(new URL(config.getRemoteURL()), cap, log);
		  }
		  else{
			  File chromeDriver = new File("drivers\\chromedriver.exe");
			  File IEDriver = new File("drivers\\IEDriverServer.exe");
			if(config.getBrowserName().equals("firefox"))
				return new FirefoxDriver();
			if(config.getBrowserName().equals("chrome")){
				if(!chromeDriver.exists()) throw new FileNotFoundException("chromedriver.exe not found under 'drivers' folder");
				System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
				return new ChromeDriver();
			}
			if(config.getBrowserName().equals("internet explorer")){
				if(!IEDriver.exists()) throw new FileNotFoundException("IEDriverServer.exe is not found under 'drivers' folder");
				System.setProperty("webdriver.ie.driver", IEDriver.getAbsolutePath());
				return new InternetExplorerDriver();
			}
		  }
		  return new FirefoxDriver();
		  
	  }
	  protected Response execute(String driverCommand, Map<String, ?> parameters)
	  {
	    Response response = super.execute(driverCommand, parameters);
	    Command command = new Command(super.getSessionId(), driverCommand, 
	      parameters);
	    log.write("Executing: " + command.toString());
	    return response;
	  }

}
