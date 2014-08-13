package com.qamate.selenium_helper.base;

import java.net.URL;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;



public class iWebDriver extends RemoteWebDriver {
	
	protected iWebDriver() {}
	  
	  public iWebDriver(CommandExecutor executor, Capabilities desiredCapabilities)
	  {
	    super(executor, desiredCapabilities);
	  }
	  
	  public iWebDriver(Capabilities desiredCapabilities)
	  {
	    super(desiredCapabilities);
	  }
	  
	  public iWebDriver(URL remoteAddress, Capabilities desiredCapabilities)
	  {
	    super(remoteAddress, desiredCapabilities);
	  }
	  
	  protected Response execute(String driverCommand, Map<String, ?> parameters)
	  {
	    Response response = super.execute(driverCommand, parameters);
	    Command command = new Command(super.getSessionId(), driverCommand, 
	      parameters);
	    System.out.println("Executing: " + command.toString());
	    //e.a(command.toString());
	    return response;
	  }

}
