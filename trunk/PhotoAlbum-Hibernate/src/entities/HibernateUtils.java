package entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	private static SessionFactory mSessionFactory = null;

	/**
	 * Opens new session and start new transaction in it. 
	 */
	public static Session startSession() {

		// Create the session factory (on demand)
		if (mSessionFactory == null) {
			// Load the settings from hibernate.cfg.xml
			Configuration cfg = new Configuration();
			cfg.configure();
			
			// Create Hibernate SessionFactory
			mSessionFactory = cfg.buildSessionFactory();
		}

		// Start new Hibernate session
		Session session = mSessionFactory.openSession();
		
		// Start new transaction (this is always required)
		session.beginTransaction();
		return session;
	}

	/**
	 * Commits the active transaction and closes the sesion. 
	 */
	public static void commit(Session session) {
		Transaction tran = session.getTransaction();
		tran.commit();
		session.beginTransaction();
	}

	/**
	 * Rollbacks the active transaction and closes the sesion. 
	 */
	public static void rollback(Session session) {
		if (session.isOpen()) {
			Transaction tran = session.getTransaction();
			if (tran.isActive()) {
				tran.rollback();
			}
			session.close();
		}
	}
	
}
