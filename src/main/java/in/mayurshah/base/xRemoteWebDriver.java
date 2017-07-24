package in.mayurshah.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import in.mayurshah.DTO.WebDriverConfig;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import in.mayurshah.util.Log;

/**
 * This file extends RemoteWebDriver. This is mainly to generate the log and manage instance of existing driver.
 */

public class xRemoteWebDriver extends RemoteWebDriver {

	  
	  public xRemoteWebDriver(CommandExecutor executor, Capabilities desiredCapabilities,Log log)
	  {
	    super(executor, desiredCapabilities);
	  }
	  
	  public xRemoteWebDriver(Capabilities desiredCapabilities,Log log)
	  {
	    super(desiredCapabilities);
	  }
	  
	  public xRemoteWebDriver(URL remoteAddress, Capabilities desiredCapabilities, Log log)
	  {
	    super(remoteAddress, desiredCapabilities);
	  }
	  public static WebDriver getInstance(WebDriverConfig config, Log log) throws MalformedURLException, FileNotFoundException{
		  if(!config.isIntenal()){
			  DesiredCapabilities cap = config.getDesiredCapabilities();
			  return 
					  new xRemoteWebDriver(new URL(config.getRemoteURL()), cap, log);
		  }
		  else{
			  File chromeDriver = new File("drivers\\chromedriver.exe");
			  File IEDriver = new File("drivers\\IEDriverServer.exe");
			if(config.getDesiredCapabilities().getBrowserName().equals("firefox"))
				return new FirefoxDriver();
			if(config.getDesiredCapabilities().getBrowserName().equals("chrome")){
				if(!chromeDriver.exists()) throw new FileNotFoundException("chromedriver.exe not found under 'drivers' folder");
				System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
				if(config.getChromeOptions() != null)
					return new ChromeDriver(config.getChromeOptions());
				return new ChromeDriver();
			}
			if(config.getDesiredCapabilities().getBrowserName().equals("internet explorer")){
				if(!IEDriver.exists()) throw new FileNotFoundException("IEDriverServer.exe is not found under 'drivers' folder");
				System.setProperty("webdriver.ie.driver", IEDriver.getAbsolutePath());
				return new InternetExplorerDriver();
			}
		  }
		  return new FirefoxDriver();
		  
	  }
}
