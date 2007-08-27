package hibernate.utils;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateConnection {
	
	private Session hbSession = null;
	
	private Transaction transaction = null;
	
	private Session getHbSession() {
		if (this.hbSession == null) {
			this.hbSession = HibernateConnectionManager.getInstance().openSession();
		}
		return this.hbSession;
	}
	
	private Transaction getTransaction() {
		if (this.transaction == null) {
			this.transaction = this.getHbSession().beginTransaction();
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
		Transaction transaction = this.getHbSession().beginTransaction();
		this.setTransaction(transaction);
	}
	
	public void commit() {
		this.getTransaction().commit();
	}
	
	public void rollback() {
		this.getTransaction().rollback();
	}
	
	public void close() {
		HibernateConnectionManager.getInstance().closeSession(this.getHbSession());
	}
	
	public void save(Object obj) {
		this.getHbSession().save(obj);
	}
	
	public void update(Object obj) {
		this.getHbSession().update(obj);
	}
	
	public void delete(Object obj) {
		this.getHbSession().delete(obj);
	}
	
	public Query createQuery(String hql) {
		return this.getHbSession().createQuery(hql);
	}
}
