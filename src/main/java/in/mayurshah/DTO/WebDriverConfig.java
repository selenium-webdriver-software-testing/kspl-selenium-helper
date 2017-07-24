package in.mayurshah.DTO;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverConfig {
	private boolean intenal;
	private String remoteURL;
	private String TestCaseName;
	private String browserName;
	private String OS;
	private String browserVersion;
	private DesiredCapabilities desiredCapabilities;
	private ChromeOptions chromeOptions;

	public WebDriverConfig(){
		this.desiredCapabilities = new DesiredCapabilities();
	}

	public void setChromeOptions(ChromeOptions chromeOptions){
		this.chromeOptions = chromeOptions;
	}

	public ChromeOptions getChromeOptions(){
		return this.chromeOptions;
	}
	public boolean isIntenal() {
		return intenal;
	}
	public void setIntenal(boolean intenal) {
		this.intenal = intenal;
	}

	public void appendCapability(String key, Object value){
		this.desiredCapabilities.setCapability(key,value);
	}
	public void appendCapability(String key, String value){
		this.desiredCapabilities.setCapability(key,value);
	}

	public DesiredCapabilities getDesiredCapabilities(){
		this.desiredCapabilities.setBrowserName(this.browserName);
		this.desiredCapabilities.setPlatform(Platform.fromString(this.OS));
		this.desiredCapabilities.setVersion(this.browserVersion);
		return desiredCapabilities;
	}

	public String getRemoteURL() {
		return remoteURL;
	}
	public void setRemoteURL(String remoteURL) {
		this.remoteURL = remoteURL;
	}
	public String getTestCaseName() {
		return TestCaseName;
	}
	public void setTestCaseName(String testCaseName) {
		TestCaseName = testCaseName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public void setOS(String oS) {
		OS = oS;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	

}
