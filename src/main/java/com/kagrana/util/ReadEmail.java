package com.kagrana.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class ReadEmail {
	public static List<String> extract(Log log, String host, String user,
		      String password) 
		   {
			List<String> strMessages = new ArrayList<String>();
			  try {
		      //create properties field
		      Properties properties = new Properties();
		      properties.setProperty("mail.store.protocol", "imaps");
		      Session emailSession = Session.getDefaultInstance(properties,null);
		      Store store = emailSession.getStore("imaps");
		      store.connect(host, user, password);
		      //create the folder object and open it
		      Folder emailFolder = store.getFolder("INBOX");
		      if(!emailFolder.isOpen())
		    	  emailFolder.open(Folder.READ_WRITE);
		      Exception error = null;
		      try{
		    	  // retrieve the messages from the folder in an array and print it
		          Message[] messages = emailFolder.getMessages();
			      if(emailFolder.getUnreadMessageCount() == 0)
			    	  log.write("No new message");
			      else
				      for (int i = emailFolder.getMessageCount() - emailFolder.getUnreadMessageCount() , n = emailFolder.getMessageCount(); i < n; i++) {
				         Message message = messages[i];
				         log.write("-------------------------------------");
				         log.write("email number : "+(i+1));
				         log.write("subject : " + message.getSubject());
				         log.write("email body : " + message.getContent().toString());
				         strMessages.add(MiscellaneousFunctions.bodyFromMimeType(message.getContent()));
				         message.setFlag(Flag.SEEN, true);
				         message.setFlag(Flag.DELETED, true);
				      }
		      }catch(Exception e){
		    	  error = e;
		      }
		      //close the store and folder objects
		      emailFolder.close(false);
		      store.close();
		      if(error !=null)
		    	  throw error;

		      } catch (NoSuchProviderException e) {
		         e.printStackTrace();
		      } catch (MessagingException e) {
		         e.printStackTrace();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		      return strMessages;
		   }
}
