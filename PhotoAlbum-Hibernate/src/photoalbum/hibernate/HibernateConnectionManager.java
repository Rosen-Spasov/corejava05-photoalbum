package photoalbum.hibernate;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import photoalbum.logging.Logger;


public class HibernateConnectionManager {
	
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
	
	private static HibernateConnectionManager defaultInstance = null;
	
	public static synchronized HibernateConnectionManager getDefaultInstance() {
		if (defaultInstance == null) {
			defaultInstance = new HibernateConnectionManager();
		}
		return defaultInstance;
	}
	
	public HibernateConnectionManager() {
	}
	
	private Configuration cfg = null;

	private SessionFactory sessionFactory = null;
	
	private ArrayList<HibernateConnection> connections = null;
	
	private boolean configured = false;
	
	private Configuration getCfg() {
		if (this.cfg == null) {
			this.cfg = new Configuration();
		}
		return this.cfg;
	}
	
	private SessionFactory getSessionFactory() {
		if (this.sessionFactory == null) {
			if (!isConfigured()) {
				configure();
			}
			this.sessionFactory = getCfg().buildSessionFactory(); 
		}
		return this.sessionFactory;
	}
	
	private ArrayList<HibernateConnection> getConnections() {
		if (this.connections == null) {
			this.connections = new ArrayList<HibernateConnection>();
		}
		return this.connections;
	}
	
	public boolean isConfigured() {
		return this.configured;
	}

	private void setConfigured(boolean configured) {
		this.configured = configured;
	}
	
	private HibernateConnection findAvailableConnection() {
		HibernateConnection connection = null;
		for (int index = 0; index < getConnections().size(); index++) {
			if (getConnections().get(index).isReleased()) {
				connection = getConnections().get(index);
				break;
			}
		}
		return connection;
	}
	
	public synchronized HibernateConnection openConnection() {
		HibernateConnection connection = findAvailableConnection();
		if (connection == null) {
			Session session = getSessionFactory().openSession();
			try {
				connection = new HibernateConnection(this, session);
			} catch (IllegalArgumentException exc) {
				Logger.getDefaultInstance().log(exc);
			}
			getConnections().add(connection);
		}
		return connection;
	}
	
	public synchronized void closeConnection(HibernateConnection connection) {
		if (getConnections().size() > CONNECTION_POOL_SIZE) {
			getConnections().remove(connection);
		}
	}
	
	public void configure() {
		getCfg().configure(CONFIG_FILE);
		setConfigured(true);
	}
	
	public void configure(String host, String port, String sid, String dbProvider) {
		if ( dbProvider.compareToIgnoreCase(DBProvider.ORACLE.toString()) == 0 ) {
			configure(host, port, sid, DBProvider.ORACLE);
		}
	}
	
	public void configure(String host, String port, String sid, DBProvider dbProvider) {
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
	
	public void initialize() throws Throwable {
		try {
			for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
				openConnection();
			}
			setConfigured(true);
		} catch (Throwable e) {
			Logger.getDefaultInstance().log(e);
			reset();
			throw e;
		}
	}
	
	public void reset() {
		cfg = null;
		sessionFactory = null;
		connections = null;
		setConfigured(false);
	}
	
	public boolean accessGranted(String password) {
		return getCfg().getProperty(PASSWORD_PROPERTY).equals(password);
	}
	
}
