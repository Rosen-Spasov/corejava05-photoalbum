package photoalbum.security;

import photoalbum.entities.User;
import photoalbum.hibernate.HibernateConnection;
import photoalbum.hibernate.HibernateConnectionManager;
import photoalbum.logging.Logger;

public class SecurityManager {
	
	private static Logger logger = null;
	
	private static SecurityManager defaultInstance = null;
	
	private static synchronized Logger getLogger() {
		if (logger == null) {
			logger = Logger.getLogger("security.log");
		}
		return logger;
	}
	
	public static synchronized SecurityManager getDefaultInstance() {
		if (defaultInstance == null) {
			defaultInstance = new SecurityManager();
		}
		return defaultInstance;
	}
	
	private HibernateConnectionManager hibernateConnectionManager = null;
	
	private HibernateConnection hbConnection = null;
	
	private HibernateConnectionManager getHibernateConnectionManager() {
		if (this.hibernateConnectionManager == null) {
			this.hibernateConnectionManager = HibernateConnectionManager.getDefaultInstance();
		}
		return this.hibernateConnectionManager;
	}
	
	private HibernateConnection getHbConnection() {
		if (this.hbConnection == null || this.hbConnection.isReleased()) {
			this.hbConnection = getHibernateConnectionManager().openConnection();
		}
		return this.hbConnection;
	}
	
	public boolean accessGranted(String userName, String password) {
		boolean result = false;
		
		User user = getHbConnection().getUserByUserName(userName);
		if (user != null && user.getPassword().equals(password)) {
			result = true;
		}
		getHbConnection().close();

		return result;
	}
	
}
