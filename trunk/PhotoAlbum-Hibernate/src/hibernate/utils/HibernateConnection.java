package hibernate.utils;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateConnection {
	
	private Session session = null;
	
	private Transaction transaction = null;
	
	private boolean available = false;
	
	public HibernateConnection(Session session) {
		this.setSession(session);
	}
	
	public boolean isAvailable() {
		return this.available;
	}
	
	private void setAvailable(boolean available) {
		this.available = available;
	}
	
	private Session getSession() {
		if (this.session == null) {
		}
		return this.session;
	}
	
	private void setSession(Session session) {
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
	
	private void setTransaction(Transaction transaction) {
		if (transaction != null) {
			this.transaction = transaction;
		} else {
			throw new IllegalArgumentException("[null] is not a valid argument for [void setTransaction(Transaction)].");
		}
	}
	
	public void beginTransaction() {
		Transaction transaction = this.getSession().beginTransaction();
		this.setTransaction(transaction);
	}
	
	public void commit() {
		this.getTransaction().commit();
	}
	
	public void rollback() {
		this.getTransaction().rollback();
	}
	
	public void close() {
		this.setAvailable(true);
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
	
	public Query createQuery(String hql) {
		return this.getSession().createQuery(hql);
	}
	
}
