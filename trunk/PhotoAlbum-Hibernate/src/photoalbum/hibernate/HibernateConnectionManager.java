package photoalbum.hibernate;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import photoalbum.logging.Logger;


public abstract class HibernateConnectionManager {
	
	public static enum DBProvider {ORACLE, MICROSOFT}
	
	public static final String CONFIG_FILE = "/hibernate.cfg.xml";
	
	public static final String USER_MAPPING = "./hbm.xml/User.hbm.xml";
	
	public static final String CATEGORY_MAPPING = "./hbm.xml/Category.hbm.xml";
	
	public static final String COMMENT_MAPPING = "./hbm.xml/Comment.hbm.xml";
	
	public static final String PHOTO_MAPPING = "./hbm.xml/Photo.hbm.xml";
	
	public static final String DRIVER_CLASS_PROPERTY = "hibernate.connection.driver_class";	
	
	public static final String URL_PROPERTY = "hibernate.connection.url";
	
	public static final String USERNAME_PROPERTY = "hibernate.connection.username";
	
	public static final String PASSWORD_PROPERTY = "hibernate.connection.password";
	
	public static final String DIALECT_PROPERTY = "hibernate.dialect";
	
	public static int CONNECTION_POOL_SIZE = 20;
	
	private static Configuration cfg = null;

	private static SessionFactory sessionFactory = null;
	
	private static ArrayList<HibernateConnection> connections = null;
	
	private static boolean configured = false;
	
	static {
		for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
			openConnection();
		}
	}
	
	private static Configuration getCfg() {
		if (cfg == null) {
			cfg = new Configuration();
		}
		return cfg;
	}
	
	private static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			if (!isConfigured()) {
				configure();
			}
			sessionFactory = getCfg().buildSessionFactory(); 
		}
		return sessionFactory;
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
			Session session = getSessionFactory().openSession();
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
		getCfg().configure(CONFIG_FILE);
		setConfigured(true);
	}
	
	public static void configure(String host, String port, String sid, String dbProvider) {
		if ( dbProvider.compareToIgnoreCase(DBProvider.ORACLE.toString()) == 0 ) {
			configure(host, port, sid, DBProvider.ORACLE);
		}
	}
	
	public static void configure(String host, String port, String sid, DBProvider dbProvider) {
		if (!isConfigured()) {
			if (dbProvider == DBProvider.ORACLE) {
				String dialect = "org.hibernate.dialect.OracleDialect";
				String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@" + host + ":" + port + "/" + sid;

				getCfg().setProperty(DRIVER_CLASS_PROPERTY, driver);
				getCfg().setProperty(DIALECT_PROPERTY, dialect);
				getCfg().setProperty(URL_PROPERTY, url);
			}
			getCfg().addResource(USER_MAPPING);
			getCfg().addResource(CATEGORY_MAPPING);
			getCfg().addResource(COMMENT_MAPPING);
			getCfg().addResource(PHOTO_MAPPING);
			setConfigured(true);
		}
	}
	
	public static boolean adminAccessGranted(String password) {
		return getCfg().getProperty(PASSWORD_PROPERTY).equals(password);
	}
	
}
