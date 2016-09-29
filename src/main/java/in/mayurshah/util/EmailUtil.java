package in.mayurshah.util;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.smtp.SMTPTransport;

public class EmailUtil{
	private Log log;
	private String to;
	private String from;
	private String password;
	private String host;
	private String port;
	private String fileAttachment;
	private boolean enableSSL;
	private PropertyFileManager pfm;
	private Session session;

	public EmailUtil(Log log){
		this.log = log;
		try {
			this.pfm = new PropertyFileManager(this.log);
		} catch (IOException e) {
			log.writeError("Property file does not exist! Not sending an email.");
			log.write(e);
		}
		if(pfm!=null){
			this.setHost(pfm.getProperty("smtpserver"));
			this.port = pfm.getProperty("smtpport");
			this.setEnableSSL(Boolean.parseBoolean(pfm.getProperty("smtpenablessl")));
			this.setFrom(pfm.getProperty("smtpusername"));
			this.setPassword(pfm.getProperty("smtppassword"));
			if(pfm.getProperty("sendemail").equals("false"))
				this.pfm = null;
		}
	}

	public void sendEmail(String toEmail, String fileAttachment) {
		if(this.pfm == null) return;
		this.setTo(toEmail);
		this.setFileAttachment(fileAttachment);
		Properties props = new Properties();
		//props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.starttls.enable", this.isEnableSSL());
		//props.put("mail.smtp.host", this.getHost());
		//props.put("mail.smtp.port", this.port);

		session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(getFrom(),
								getPassword());
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(getFrom()));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(this.getTo()));

			// Set Subject: header field
			message.setSubject("Report");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart
					.setText("Kindly find the attached detaild report with this email.");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = this.getFileAttachment();
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			send(message);

			log.write("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	private void send(Message msg) throws MessagingException
	  {
	    SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
	    try
	    {
	      t.connect(this.getHost(), this.getFrom(), this.getPassword());
	      t.sendMessage(msg, msg.getAllRecipients());
	    } finally
	    {
	      System.out.println("Response: " + t.getLastServerResponse());
	      t.close();
	    }
	  }
	public String getFileAttachment() {
		return fileAttachment;
	}

	public void setFileAttachment(String fileAttachment) {
		this.fileAttachment = fileAttachment;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public boolean isEnableSSL() {
		return enableSSL;
	}

	public void setEnableSSL(boolean enableSSL) {
		this.enableSSL = enableSSL;
	}
}
