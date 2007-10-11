package photoalbum.mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import photoalbum.logging.Logger;

public class MailSender {
	
	private static MailSender instance;
	
	private MailConfiguration mailConfiguration;
	
	private Session mailSession;
	
	private Logger logger = Logger.getDefaultInstance();
	
	private MailSender() {
		init();
	}

	public static MailSender getInstance() {
		if (instance == null) {
			instance = new MailSender();
		}
		return instance;
	}

	public Logger getLogger() {
		if (logger == null) {
			logger = Logger.getDefaultInstance();
		}
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public MailConfiguration getMailConfiguration() {
		if (mailConfiguration == null) {
			mailConfiguration = MailConfiguration.getInstance();
		}
		return mailConfiguration;
	}

	public void setMailConfiguration(MailConfiguration mailConfiguration) {
		this.mailConfiguration = mailConfiguration;
	}

	public Session getMailSession() {
		if (mailSession == null) {
			Properties props = getMailConfiguration().getProperties();
			Authenticator authenticator = getMailConfiguration().getSmtpAuthenticator();
			mailSession = Session.getDefaultInstance(props, authenticator);
		}
		return mailSession;
	}

	public void setMailSession(Session mailSession) {
		this.mailSession = mailSession;
	}

	private void init() {
		getMailConfiguration().configure();
		
		Properties props = getMailConfiguration().getProperties();
		Authenticator authenticator = getMailConfiguration().getSmtpAuthenticator();
		Session mailSession = Session.getDefaultInstance(props, authenticator);
		try {
			PrintStream debugOutput = new PrintStream(Logger.SENT_MAILS_LOG);
			mailSession.setDebugOut(debugOutput);
		} catch (FileNotFoundException e) {
			getLogger().log(e);
		}
		setMailSession(mailSession);
	}
	
	public void sendFile(String fileName, String sender, String recipientAddress) {
		if (fileName == null || sender == null ||recipientAddress == null ||
			fileName.equals("") || sender.equals("") || recipientAddress.equals("")) {
			return;
		}
		try {
			MimeMessage message = new MimeMessage(getMailSession());
			
			Address fromAddress = new InternetAddress(getMailConfiguration().getSender());
			message.setFrom(fromAddress);
			Address toAddress = new InternetAddress(recipientAddress);
			message.setRecipient(RecipientType.TO, toAddress);
			
			String subject = sender + " Sends You a Photo";
			message.setSubject(subject);
			
			MimeBodyPart bodyPartText = new MimeBodyPart();
			String text = "The photo is attached to this e-mail.";
			bodyPartText.setText(text);
			
			MimeBodyPart bodyPartAttachement = new MimeBodyPart();
			bodyPartAttachement.attachFile(fileName);
			
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(bodyPartText);
			mp.addBodyPart(bodyPartAttachement);
			
			message.setContent(mp);
			
			Transport.send(message);
			
		} catch (AddressException e) {
			getLogger().log(e);
		} catch (MessagingException e) {
			getLogger().log(e);
		} catch (IOException e) {
			getLogger().log(e);
		}
	}

}
