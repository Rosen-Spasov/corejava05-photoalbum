package photoalbum.hibernate.utils;

import java.util.ArrayList;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import photoalbum.common.Common.DBProvider;
import photoalbum.logging.Logger;


public class HibernateConnectionManager {
	
	public static final String CONFIG_FILE = "/hibernate.cfg.xml";
	
	public static final String DRIVER_CLASS_PROPERTY = "connection.driver_class";
	
	public static final String URL_PROPERTY = "connection.url";
	
	public static final String USERNAME_PROPERTY = "connection.username";
	
	public static final String PASSWORD_PROPERTY = "connection.password";
	
	public static int CONNECTION_POOL_SIZE = 20;
	
	static {
		for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
			openConnection();
		}
	}
	
	private static Configuration cfg = null;

	private static SessionFactory sessionFactory;
	
	private static ArrayList<HibernateConnection> connections = null;
	
	private static boolean configured = false;
	
	private static Configuration getCfg() {
		if (cfg == null) {
			cfg = new Configuration();
		}
		return cfg;
	}
	
	private static SessionFactory getSessionFactory() {
		if (HibernateConnectionManager.sessionFactory == null) {
			if (!isConfigured()) {
				configure();
			}
			HibernateConnectionManager.sessionFactory = getCfg().buildSessionFactory(); 
		}
		return HibernateConnectionManager.sessionFactory;
	}
	
	private static ArrayList<HibernateConnection> getConnections() {
		if (connections == null) {
			connections = new ArrayList<HibernateConnection>();
		}
		return connections;
	}
	
	public static boolean isConfigured() {
		return configured;
	}

	private static void setConfigured(boolean configured) {
		HibernateConnectionManager.configured = configured;
	}

	private HibernateConnectionManager() {
	}
	
	private static HibernateConnection findAvailableConnection() {
		HibernateConnection connection = null;
		for (int index = 0; index < getConnections().size(); index++) {
			if (getConnections().get(index).isReleased()) {
				connection = getConnections().get(index);
				break;
			}
		}
		return connection;
	}
	
	public static synchronized HibernateConnection openConnection() {
		HibernateConnection connection = findAvailableConnection();
		if (connection == null) {
			Session session = HibernateConnectionManager.getSessionFactory().openSession();
			try {
				connection = new HibernateConnection(session);
			} catch (IllegalArgumentException exc) {
				Logger.getDefaultInstance().log(exc);
			}
			getConnections().add(connection);
		}
		return connection;
	}
	
	public static synchronized void closeConnection(HibernateConnection connection) {
		if (getConnections().size() > CONNECTION_POOL_SIZE) {
			getConnections().remove(connection);
		}
	}
	
	public static void configure() {
		if (!isConfigured()) {
			getCfg().configure(CONFIG_FILE);
			setConfigured(true);
		}
	}
	
	public static void configure(String password, String host, String port, String sid, String dbProvider) {
		if ( dbProvider.equals(DBProvider.ORACLE.toString()) ) {
			configure(password, host, port, sid, DBProvider.ORACLE);
		}
	}
	
	public static void configure(String password, String host, String port, String sid, DBProvider dbProvider) {
		if (!isConfigured()) {
			if (dbProvider == DBProvider.ORACLE) {
				String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@" + host + ":" + port + "/" + sid;
				getCfg().setProperty(DRIVER_CLASS_PROPERTY, driver);
				getCfg().setProperty(URL_PROPERTY, url);
			}
			if (!password.equals("")) {
				getCfg().setProperty(PASSWORD_PROPERTY, password);
			}
			setConfigured(true);
		}
	}
	
}
