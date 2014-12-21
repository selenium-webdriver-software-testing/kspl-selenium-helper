package com.kagrana.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.sql.Timestamp;

public class MiscellaneousFunctions {
	/**
	 * This method helps form URL for webdriver
	 * 
	 * @param remoteURL
	 * @param port
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL getWebDriverURL(String remoteURL, int port)
			throws MalformedURLException {
		if (port > 0 && remoteURL != null && !remoteURL.equals("")) {
			if (!remoteURL.startsWith("http://")) {
				return new URL("http://" + remoteURL + ":" + port + "/wd/hub");
			} else
				return new URL(remoteURL + ":" + port + "/wd/hub");
		}
		return null;
	}

	/**
	 * This function is mainly used by TestNG data provider
	 * 
	 * @param mapList
	 * @param log
	 * @return
	 */
	public static Object[][] listHashMapToObject(
			List<HashMap<String, String>> mapList) {
		Object[][] data = new Object[mapList.size()][1];
		{
			for(int i=0;i<mapList.size();i++)
				data[i][0] = mapList.get(i);
		}
		return data;
	}

	public static List<String> USPhoneNumber(String string) {
		List<String> phoneNumbers = new ArrayList<String>();
		String regexPhone = "\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})";
		Pattern paternPhone = Pattern.compile(regexPhone);
		Matcher matcherPhone = paternPhone.matcher(string);
		while (matcherPhone.find()) {
			phoneNumbers.add(matcherPhone.group());
		}
		return phoneNumbers;
	}

	public static List<String> emailAddresses(String string) {
		List<String> emails = new ArrayList<String>();
		String regexEmail = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
		Pattern patternEmail = Pattern.compile(regexEmail);
		Matcher matcherEmail = patternEmail.matcher(string);
		while (matcherEmail.find()) {
			emails.add(matcherEmail.group());
		}
		return emails;
	}

	public static String getTimeStamp() {
		java.util.Date date = new java.util.Date();
		return new Timestamp(date.getTime()).toString();
	}

	public static String bodyFromMimeType(Object message)
			throws MessagingException, IOException {
		String body = message.toString();
		if (message instanceof Multipart) {
			Multipart multipart = (Multipart) message;
			for (int x = 0; x < multipart.getCount(); x++) {
				BodyPart bodyPart = multipart.getBodyPart(x);
				String disposition = bodyPart.getDisposition();
				if (disposition != null
						&& disposition.equals(BodyPart.ATTACHMENT))
					continue;
				else {
					return bodyPart.getContent().toString();
				}

			}
		}
		return body;
	}

	public static String getFileNameFromURL(String URL) {
		String fileName = "";
		String[] path = URL.split("/");
		if (URL.endsWith("/"))
			fileName = path[path.length - 1];
		else {
			String[] fileNameSplit = path[path.length - 1].split(".");
			if (fileNameSplit.length > 0)
				fileName = path[path.length - 1];
		}
		return fileName;
	}

	public static String captureScreenshot(WebDriver driver,
			String screenshotdir) throws IOException {
		String randomUUID = UUID.randomUUID().toString();
		String storeFileName = screenshotdir + "\\"
				+ getFileNameFromURL(driver.getCurrentUrl()) + "_"
				+ randomUUID + ".png";
		String[] screenshotdirsplit = screenshotdir.split("\\\\");
		String fileName = screenshotdirsplit[screenshotdirsplit.length - 1] + "\\"
				+ getFileNameFromURL(driver.getCurrentUrl()) + "_"
				+ randomUUID + ".png";
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(storeFileName));
		return fileName;
	}
}
