package hibernate.queries;

import hibernate.utils.HibernateConnection;
import hibernate.utils.HibernateConnectionManager;

import java.util.List;

import logging.Logger;

import org.hibernate.Query;

import entities.User;

public class UserQuery {
	
	Logger logger = null;
	
	HibernateConnection hbConnection = null;

	private Logger getLogger() {
		if (this.logger == null) {
			this.logger = Logger.getLogger("queries.log");
		}
		return this.logger;
	}

	private HibernateConnection openHbConnection() {
		if (this.hbConnection == null || !this.hbConnection.isAvailable()) {
			this.hbConnection = HibernateConnectionManager.openConnection();
		}
		return this.hbConnection;
	}
	
	private void closeHbConnection() {
		HibernateConnectionManager.closeConnection(this.hbConnection);
		this.hbConnection = null;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		List<User> result = null;
		
		String hql = "from User";
		Query query = this.openHbConnection().createQuery(hql);
		result = (List<User>) query.list();
		this.closeHbConnection();
		
		return result;
	}
	
	
	public User getUserByID(int userID) {
		User result = (User) this.openHbConnection().get(User.class, userID);
		this.closeHbConnection();
		return result;
	}
	
	public User getUserByUserName(String userName) {
		User user = null;
		
		String hql = "from User u where u.UserName=:userName";
		Query query = this.openHbConnection().createQuery(hql);
		query.setString("userName", userName);
		List list = (List) query.list();
		if (list != null && list.size() != 1) {
			try {
				user = (User) list.get(0);
			} catch (ClassCastException exc) {
				this.logger.log(exc);
			}
		} else {
			this.getLogger().log("ERROR: Cannot find User with username=" + userName + " or there is more than one User with the given username.");
		}
		this.closeHbConnection();
		
		return user;
	}

}
