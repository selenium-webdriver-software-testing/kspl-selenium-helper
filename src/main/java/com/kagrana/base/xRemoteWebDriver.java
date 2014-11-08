package com.kagrana.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;

import com.kagrana.DTO.WebDriverConfig;
import com.kagrana.util.Log;
import com.kagrana.util.MiscellaneousFunctions;



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
	  public static WebDriver getInstance(WebDriverConfig config,Log log) throws MalformedURLException{
		  if(!config.isIntenal()){
			  DesiredCapabilities cap = new DesiredCapabilities();
			  cap.setBrowserName(config.getBrowserName());
			  cap.setPlatform(Platform.valueOf(config.getOS()));
			  cap.setVersion(config.getBrowserVersion());
			  return 
					  new xRemoteWebDriver(MiscellaneousFunctions.getWebDriverURL(config.getRemoteURL(), config.getRemotePort()), cap, log);
		  }
		  else 
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
