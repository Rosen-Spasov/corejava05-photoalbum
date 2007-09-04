package photoalbum.security;

import photoalbum.entities.Category;
import photoalbum.entities.User;
import photoalbum.hibernate.utils.HibernateConnection;
import photoalbum.hibernate.utils.HibernateConnectionManager;
import photoalbum.logging.Logger;

public class SecurityManager {
	
	private static Logger logger = null;
	
	private static HibernateConnection hbConnection = null;
	
	private static synchronized HibernateConnection getHbConnection() {
		if (hbConnection == null || hbConnection.isReleased()) {
			hbConnection = HibernateConnectionManager.openConnection();
		}
		return hbConnection;
	}
	
	private static synchronized Logger getLogger() {
		if (logger == null) {
			logger = Logger.getLogger("security.log");
		}
		return logger;
	}
	
	public static synchronized boolean accessGranted(String userName, String password) {
		boolean result = false;
		
		User user = getHbConnection().getUserByUserName(userName);
		if (user != null && user.getPassword().equals(password)) {
			result = true;
		}
		getHbConnection().close();

		return result;
	}
	
//	public static synchronized boolean categoryExists(Category category) {		
//		String path = category.getPath();
//		return categoryExists(path);
//	}
//	
//	public static synchronized boolean categoryExists(String path) {
//		if (getHbConnection().getCategoryByPath(path) == null) {
//			return false;
//		}		
//		return true;
//	}
}
