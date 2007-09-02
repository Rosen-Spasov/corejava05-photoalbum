package security;

import hibernate.utils.HibernateConnection;
import hibernate.utils.HibernateConnectionManager;
import logging.Logger;
import entities.User;

public class SecurityManager {
	
	private Logger logger = null;
	
	private HibernateConnection hbConnection = null;
	
	private HibernateConnection getHbConnection() {
		if (this.hbConnection == null || this.hbConnection.isReleased()) {
			this.hbConnection = HibernateConnectionManager.openConnection();
		}
		return this.hbConnection;
	}
	
	private Logger getLogger() {
		if (this.logger == null) {
			this.logger = Logger.getLogger("security.log");
		}
		return this.logger;
	}
	
	public boolean accessGranted(String userName, String password) {
		boolean result = false;
		
		User user = this.getHbConnection().getUserByUserName(userName);
		if (user != null && user.getPassword().equals(password)) {
			result = true;
		}
		this.getHbConnection().close();

		return result;
	}
}
