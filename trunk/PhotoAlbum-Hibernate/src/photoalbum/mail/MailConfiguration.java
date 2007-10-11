package photoalbum.mail;

import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MailConfiguration {
	
	public static final String CONF_XML = "./mailConf.xml";
	
	public static final String ROOT_TAG = "/mail";
	
	public static final String SMTP_TAG = ROOT_TAG + "/smtp";
	
	public static final String SMTP_HOST_TAG = SMTP_TAG+ "/host";
	
	public static final String SMTP_PORT_TAG = SMTP_TAG+ "/port";
	
	public static final String SMTP_START_TLS_TAG = SMTP_TAG+ "/startTls";
	
	public static final String SMTP_AUTH_TAG = SMTP_TAG+ "/auth";
	
	public static final String SMTP_AUTH_ENABLE_TAG = SMTP_AUTH_TAG+ "/enable";
	
	public static final String SMTP_USERNAME_TAG = SMTP_AUTH_TAG+ "/username";
	
	public static final String SMTP_PASSWORD_TAG = SMTP_AUTH_TAG+ "/password";
	
	public static final String SMTP_SENDER_TAG = SMTP_TAG+ "/sender";
	
	public static final String DEBUG_TAG = ROOT_TAG+ "/debug";
	
	public static final String PROPERTY_SMTP_HOST = "mail.smtp.host";
	
	public static final String PROPERTY_SMTP_PORT = "mail.smtp.port";
	
	public static final String PROPERTY_SMTP_START_TLS = "mail.smtp.starttls.enable";
	
	public static final String PROPERTY_SMTP_AUTH = "mail.smtp.auth";
	
	public static final String PROPERTY_SENDER = "mail.from";
	
	public static final String PROPERTY_DEBUG = "mail.smtp.debug";
	
	private static MailConfiguration instance;
	
	private String smtpHost;
	
	private int smtpPort;
	
	private boolean startTls;
	
	private boolean authenticationEnabled;
	
	private String username;
	
	private String password;
	
	private String sender;
	
	private boolean debug;
	
	private SmtpAuthenticator smtpAuthenticator;
	
	private Properties properties;
	
	private MailConfiguration() {
	}

	public static MailConfiguration getInstance() {
		if (instance == null) {
			instance = new MailConfiguration();
		}
		return instance;
	}

	public boolean isAuthenticationEnabled() {
		return authenticationEnabled;
	}

	public void setAuthenticationEnabled(boolean authenticationEnabled) {
		this.authenticationEnabled = authenticationEnabled;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public boolean isStartTls() {
		return startTls;
	}

	public void setStartTls(boolean startTls) {
		this.startTls = startTls;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
		}
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public SmtpAuthenticator getSmtpAuthenticator() {
		if (smtpAuthenticator == null) {
			smtpAuthenticator = new SmtpAuthenticator();
		}
		return smtpAuthenticator;
	}

	public void setSmtpAuthenticator(SmtpAuthenticator smtpAuthenticator) {
		this.smtpAuthenticator = smtpAuthenticator;
	}

	public void configure() {
		configure(CONF_XML);
	}
	
	public void configure(String confXml) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document confDoc = documentBuilder.parse(confXml);
			
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();
			
			String smtpHost = xPath.evaluate(SMTP_HOST_TAG, confDoc);
			setSmtpHost(smtpHost);
			
			int smtpPort = Integer.parseInt( xPath.evaluate(SMTP_PORT_TAG, confDoc) );
			setSmtpPort(smtpPort);
			
			boolean startTls = Boolean.parseBoolean( xPath.evaluate(SMTP_START_TLS_TAG, confDoc) );
			setStartTls(startTls);
			
			boolean authenticationEnabled = Boolean.parseBoolean( xPath.evaluate(SMTP_AUTH_ENABLE_TAG, confDoc) );
			if (authenticationEnabled) {
				setAuthenticationEnabled(authenticationEnabled);
				
				String username = xPath.evaluate(SMTP_USERNAME_TAG, confDoc);
				setUsername(username);
				
				String password = xPath.evaluate(SMTP_PASSWORD_TAG, confDoc);
				setPassword(password);
				
				SmtpAuthenticator smtpAuthenticator = new SmtpAuthenticator(username, password);
				setSmtpAuthenticator(smtpAuthenticator);
			}
			
			String sender = xPath.evaluate(SMTP_SENDER_TAG, confDoc);
			setSender(sender);
			
			boolean debug = Boolean.parseBoolean( xPath.evaluate(DEBUG_TAG, confDoc) );
			setDebug(debug);
			
			initProperties();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initProperties() {
		getProperties().put(PROPERTY_SMTP_HOST, getSmtpHost());
		getProperties().put(PROPERTY_SMTP_PORT, getSmtpPort());
		getProperties().put(PROPERTY_SMTP_START_TLS, isStartTls());
		getProperties().put(PROPERTY_SMTP_AUTH, isAuthenticationEnabled());
		getProperties().put(PROPERTY_SENDER, getSender());
		getProperties().put(PROPERTY_DEBUG, isDebug());
	}

}
