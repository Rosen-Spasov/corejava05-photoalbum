package hibernate.utils;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnectionManager {
	
	public static final String CONFIG_FILE = "/hibernate.cfg.xml";
	
	public static int CONNECTION_POOL_SIZE = 20;
	
//	static {
//		for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
//			openSession();
//		}
//	}
	
	private static SessionFactory sessionFactory;
	
//	private static HibernateConnectionManager instance = null;
	
	private static ArrayList<Session> activeSessions = null;
	
	private static SessionFactory getSessionFactory() {
		if (HibernateConnectionManager.sessionFactory == null) {
			Configuration cfg = new Configuration();
			cfg.configure(CONFIG_FILE);
			HibernateConnectionManager.sessionFactory = cfg.buildSessionFactory(); 
		}
		return HibernateConnectionManager.sessionFactory;
	}
	
	private static ArrayList<Session> getActiveSessions() {
		if (activeSessions == null) {
			activeSessions = new ArrayList<Session>();
		}
		return activeSessions;
	}
	
//	public static HibernateConnectionManager getInstance() {
//		if (HibernateConnectionManager.instance == null) {
//			HibernateConnectionManager.instance = new HibernateConnectionManager();
//		}
//		return HibernateConnectionManager.instance;
//	}
	
	private HibernateConnectionManager() {
	}
	
	public static synchronized Session openSession() {
		Session hbSession = HibernateConnectionManager.getSessionFactory().openSession();
		getActiveSessions().add(hbSession);
		return hbSession;
	}
	
	public static synchronized void closeSession(Session hbSession) {
		getActiveSessions().remove(hbSession);
	}
}
