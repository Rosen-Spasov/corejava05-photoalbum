package security;

import logging.Logger;

public class SecurityManager {
	
	private Logger logger = null;
	
	private Logger getLogger() {
		if (this.logger == null) {
			this.logger = new Logger("security.log");
		}
		return this.logger;
	}
	
	public boolean accessGranted(String username, String password, String type) {
		boolean result = false;
	
		return result;
	}
}
