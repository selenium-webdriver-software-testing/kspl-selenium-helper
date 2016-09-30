package in.mayurshah.util;

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

/**
 * MiscellaneousFunctions, as the name says, contains functions which can be used frequently and are independent.
 */
public class MiscellaneousFunctions {
	/**
	 * This method helps form URL for webdriver
	 * 
	 * @param remoteURL -Enter remote URL, e.g. in.mayurshah
	 * @param port - Enter your port
	 * @return - returns fully qualified Grid URL
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
	 * @param mapList -Pass the list of hasmap as parameter
	 * @return -It will return two dimension array of an Object.
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

	/**
	 * This function is used to identify US Phone number from the string
	 * @param string -Provide your string
	 * @return - Identifies phone numbers and returns the same.
     */
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

	/**
	 * This function accepts a string and identify email address and return the same.
	 * @param string -Provide string
	 * @return -list of email addresses
     */
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

	/**
	 * This function gets current Time Stamp as String
	 * @return -Time Stamp as String
     */
	public static String getTimeStamp() {
		java.util.Date date = new java.util.Date();
		return new Timestamp(date.getTime()).toString();
	}

	/**
	 * This function returns string of the body from Mime Type
	 * @param message -Pass your message
	 * @return -Get string of the body.
	 * @throws MessagingException
	 * @throws IOException
     */
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

	/**
	 * Thiss function is used to get file name from given URL
	 * @param URL -Enter URL
     * @return -Returns file name
     */
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

	/**
	 * This function is used to capture screenshot and store it in directory
	 * @param driver -Pass your WebDriver instance.
	 * @param screenshotdir - Pass your screenshot directory
	 * @return - Returns location where screenshot is stored.
	 * @throws IOException
     */
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
