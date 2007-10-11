package photoalbum.mail;

import java.io.Serializable;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator implements Serializable {

	private static final long serialVersionUID = -5754349608118330309L;
	
	private String username;
	
	private String password;
	
	private PasswordAuthentication passwordAuthentication;
	
	public SmtpAuthenticator() {
	}
	
	public SmtpAuthenticator(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	 
	public String getPassword() {
		if (password == null) {
			password = "";
		}
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		if (username == null) {
			username = "";
		}
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		if (passwordAuthentication == null) {
			passwordAuthentication = new PasswordAuthentication(username, password); 
		}
		return passwordAuthentication;
	}

	public void setPasswordAuthentication(PasswordAuthentication passwordAuthentication) {
		this.passwordAuthentication = passwordAuthentication;
	}

}
