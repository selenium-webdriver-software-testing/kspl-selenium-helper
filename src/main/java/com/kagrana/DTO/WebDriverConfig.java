package com.kagrana.DTO;

public class WebDriverConfig {
	private int remotePort;
	private boolean intenal;
	private String remoteURL;
	private String TestCaseName;
	private String browserName;
	private String OS;
	private String browserVersion;
	
	public int getRemotePort() {
		return remotePort;
	}
	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}
	public boolean isIntenal() {
		return intenal;
	}
	public void setIntenal(boolean intenal) {
		this.intenal = intenal;
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
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	

}
