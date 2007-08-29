package security;

import entities.User;
import hibernate.queries.UserQuery;
import logging.Logger;

public class SecurityManager {
	
	private Logger logger = null;
	
	private Logger getLogger() {
		if (this.logger == null) {
			this.logger = Logger.getLogger("security.log");
		}
		return this.logger;
	}
	
	public boolean accessGranted(String userName, String password) {
		boolean result = false;
		
		UserQuery query = new UserQuery();
		User user = query.getUserByUserName(userName);
		if (user != null && user.getPassword().equals(password)) {
			result = true;
		}

		return result;
	}
}
