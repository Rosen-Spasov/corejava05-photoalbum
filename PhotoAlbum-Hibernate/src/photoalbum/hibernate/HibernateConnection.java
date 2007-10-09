package photoalbum.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import photoalbum.entities.Category;
import photoalbum.entities.Comment;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.logging.Logger;


public class HibernateConnection {
	
	private Session session = null;
	
	private Transaction transaction = null;
	
	private boolean released = false;
	
	private Logger logger = null;
	
	public HibernateConnection(Session session) throws IllegalArgumentException {
		this.setSession(session);
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
		return this.transaction;
	}
	
	private void setTransaction(Transaction transaction) throws IllegalArgumentException {
		this.transaction = transaction;
	}
	
	public void beginTransaction() {
		Transaction transaction = this.getSession().beginTransaction();
		transaction.begin();
		this.setTransaction(transaction);
	}
	
	public void commit() {
		this.getTransaction().commit();
	}
	
	public void rollback() {
		this.getTransaction().rollback();
	}
	
	public void close() {
		this.setReleased(true);
		HibernateConnectionManager.closeConnection(this);
	}
	
	public void save(Object obj) {
		this.getSession().save(obj);
	}
	
	public void update(Object obj) {
		this.getSession().update(obj);
	}
	
	public void delete(Object obj) {
		this.getSession().delete(obj);
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
	
	
	public User getUserById(int userId) {
		return (User) this.get(User.class, userId);
	}
	
	public Category getCategoryById(int categoryId) {
		return (Category) this.get(Category.class, categoryId);
	}
	
	public Photo getPhotoById(int photoId) {
		return (Photo) this.get(Photo.class, photoId);
	}
	
	public Comment getCommentById(int commentId) {
		return (Comment) this.get(Comment.class, commentId);
	}
	
	public User getUserByUsername(String username) {
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
		if (path == null) {
			return null;
		}
		
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
		if (path == null) {
			return null;
		}
		
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
	
	public void refresh(Object obj) {
		if (obj != null) {
			this.getSession().refresh(obj);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Photo> searchPhotos(String queryString) {
		List<Photo> result = null;
		
		String hql = "from Photo p where p.PhName like :queryString";
		Query query = this.createQuery(hql);
		queryString = "%"+queryString+"%";
		query.setString("queryString", queryString);
		result = (List<Photo>) query.list();
		
		return result;
	}
	
}
