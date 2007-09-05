package photoalbum.hibernate;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.logging.Logger;


public class HibernateConnection {
	
	private Session session = null;
	
	private Transaction transaction = null;
	
	private boolean released = false;
	
	private Logger logger = null;
	
	private HibernateConnectionManager hibernateConnectionManager = null;
	
	public HibernateConnection(HibernateConnectionManager hibernateConnectionManager, Session session) throws IllegalArgumentException {
		this.setHibernateConnectionManager(hibernateConnectionManager);
		this.setSession(session);
	}
	
	private HibernateConnectionManager getHibernateConnectionManager() {
		if (this.hibernateConnectionManager == null) {
			this.hibernateConnectionManager = HibernateConnectionManager.getDefaultInstance();
		}
		return this.hibernateConnectionManager;
	}
	
	private void setHibernateConnectionManager(HibernateConnectionManager hibernateConnectionManager) {
		this.hibernateConnectionManager = hibernateConnectionManager;
	}
	
	private Logger getLogger() {
		if (this.logger == null) {
			this.logger = Logger.getDefaultInstance();
		}
		return this.logger;
	}
	
	public boolean isReleased() {
		return this.released;
	}
	
	private void setReleased(boolean released) {
		this.released = released;
	}
	
	private Session getSession() {
		if (this.session == null) {
		}
		return this.session;
	}
	
	private void setSession(Session session) throws IllegalArgumentException {
		if (session != null) {
			this.session = session;
		} else {
			throw new IllegalArgumentException("[null] is not a valid argument for [void setSession(Session)].");
		}
	}
	
	private Transaction getTransaction() {
		if (this.transaction == null) {
			this.transaction = this.getSession().beginTransaction();
		}
		return this.transaction;
	}
	
	private void setTransaction(Transaction transaction) throws IllegalArgumentException {
		if (transaction != null) {
			this.transaction = transaction;
		} else {
			throw new IllegalArgumentException("[null] is not a valid argument for [void setTransaction(Transaction)].");
		}
	}
	
	private void beginTransaction() {
		Transaction transaction = this.getSession().beginTransaction();
		this.setTransaction(transaction);
	}
	
	private void commit() {
		this.getTransaction().commit();
	}
	
	private void rollback() {
		this.getTransaction().rollback();
	}
	
	public void close() {
		this.setReleased(true);
		this.getHibernateConnectionManager().closeConnection(this);
	}
	
	public void save(Object obj) {
		this.beginTransaction();
		try {
			this.getSession().save(obj);
			this.commit();
		} catch (Throwable e) {
			this.rollback();
			this.getLogger().log(e);
		}
	}
	
	public void update(Object obj) {
		this.beginTransaction();
		try {
			this.getSession().update(obj);
			this.commit();
		} catch (Throwable e) {
			this.rollback();
			this.getLogger().log(e);
		}
	}
	
	public void delete(Object obj) {
		this.beginTransaction();
		try {
			this.getSession().delete(obj);
			this.commit();
		} catch (Throwable e) {
			this.rollback();
			this.getLogger().log(e);
		}
	}
	
	public Object get(Class objClass, int primaryKey) {
		return this.getSession().get(objClass, primaryKey);
	}
	
	public Query createQuery(String hql) {
		return this.getSession().createQuery(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		List<User> result = null;
		
		String hql = "from User order by Username";
		Query query = this.createQuery(hql);
		result = (List<User>) query.list();
		
		return result;
	}
	
	
	public User getUserByID(int userID) {
		return (User) this.get(User.class, userID);
	}
	
	public User getUserByUserName(String username) {
		User result = null;
		
		String hql = "from User u where u.Username=:username";
		Query query = this.createQuery(hql);
		query.setString("username", username);
		List list = (List) query.list();
		if (list != null && list.size() == 1) {
			try {
				result = (User) list.get(0);
			} catch (ClassCastException exc) {
				this.getLogger().log(exc);
			}
		} else {
			this.getLogger().log("ERROR: Cannot find User with username=" + username + " or there is more than one User with the given username.");
		}
		
		return result;
	}
	
	public Category getCategoryByPath(String path) {
		Category result = null;
		
		String hql = "from Category c where c.Path=:path";
		Query query = this.createQuery(hql);
		query.setString("path", path);
		List list = (List) query.list();
		if (list != null && list.size() == 1) {
			try {
				result = (Category) list.get(0);
			} catch (ClassCastException exc) {
				this.getLogger().log(exc);
			}
		} else {
			this.getLogger().log("ERROR: Cannot find Category with path [" + path + "] or there is more than one Category with the given path.");
		}
		
		return result;
	}
	
	public Photo getPhotoByPath(String path) {
		Photo result = null;
		
		String hql = "from Photo p where p.Path=:path";
		Query query = this.createQuery(hql);
		query.setString("path", path);
		List list = (List) query.list();
		if (list != null && list.size() == 1) {
			try {
				result = (Photo) list.get(0);
			} catch (ClassCastException exc) {
				this.getLogger().log(exc);
			}
		} else {
			this.getLogger().log("ERROR: Cannot find Photo with path [" + path + "] or there is more than one Photo with the given path.");
		}
		
		return result;
	}
	
}
