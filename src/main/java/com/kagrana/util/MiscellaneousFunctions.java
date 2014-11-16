package com.kagrana.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import java.sql.Timestamp;

public class MiscellaneousFunctions {
	/**
	 * This method helps form URL for webdriver
	 * @param remoteURL
	 * @param port
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL getWebDriverURL(String remoteURL,int port) throws MalformedURLException{
		if(port > 0 && remoteURL != null && !remoteURL.equals("")){
			if(!remoteURL.startsWith("http://")){
				return new URL("http://"+remoteURL + ":"+port+"/wd/hub");
			}
			else
				return new URL(remoteURL + ":"+port+"/wd/hub");
		}
		return null;
	}
	/**
	 * This function is mainly used by TestNG data provider
	 * @param mapList
	 * @param log
	 * @return
	 */
	public static Object[][] listHashMapToObject(List<HashMap<String, String>> mapList){
		Object[][] data = new Object[mapList.size()][1];
		{
			int i=0;
			for(HashMap<String, String> map : mapList)
				data[i++][0] = map;
		}
		return data;
	}
	public static List<String> USPhoneNumber(String string){
		List<String> phoneNumbers = new ArrayList<String>();
		String regexPhone = "\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})";
        Pattern paternPhone = Pattern.compile(regexPhone);
        Matcher matcherPhone = paternPhone.matcher(string);
        while(matcherPhone.find()){
        	phoneNumbers.add(matcherPhone.group());
        }
		return phoneNumbers;
	}
	public static List<String> emailAddresses(String string){
		List<String> emails = new ArrayList<String>();
		String regexEmail = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
        Pattern patternEmail = Pattern.compile(regexEmail);
        Matcher matcherEmail = patternEmail.matcher(string);
        while(matcherEmail.find()){
       	 		emails.add(matcherEmail.group());
        }
		return emails;
	}
	public static String getTimeStamp(){
		java.util.Date date= new java.util.Date();
		return new Timestamp(date.getTime()).toString();
	}
	public static String bodyFromMimeType(Object message) throws MessagingException, IOException{
		String body = message.toString();
		if(message instanceof Multipart){
			Multipart multipart = (Multipart) message;
			for(int x = 0; x < multipart.getCount() ; x++){
				BodyPart bodyPart = multipart.getBodyPart(x);
				String disposition = bodyPart.getDisposition();
				if(disposition !=null && disposition.equals(BodyPart.ATTACHMENT))
					continue;
				else{
					return
							bodyPart.getContent().toString();
				}
					
			}
		}
		return body;
	}
}
