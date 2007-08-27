package hibernate.utils;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnectionManager {
	
	public static final String CONFIG_FILE = "/hibernate.cfg.xml";
	
	private static SessionFactory sessionFactory;
	
	private static HibernateConnectionManager instance = null;
	
	private ArrayList<Session> activeSessions = null;
	
	private static SessionFactory getSessionFactory() {
		if (HibernateConnectionManager.sessionFactory == null) {
			Configuration cfg = new Configuration();
			cfg.configure(CONFIG_FILE);
			HibernateConnectionManager.sessionFactory = cfg.buildSessionFactory(); 
		}
		return HibernateConnectionManager.sessionFactory;
	}
	
	private ArrayList<Session> getActiveSessions() {
		if (this.activeSessions == null) {
			this.activeSessions = new ArrayList<Session>();
		}
		return this.activeSessions;
	}
	
	public static HibernateConnectionManager getInstance() {
		if (HibernateConnectionManager.instance == null) {
			HibernateConnectionManager.instance = new HibernateConnectionManager();
		}
		return HibernateConnectionManager.instance;
	}
	
	private HibernateConnectionManager() {
	}
	
	public Session openSession() {
		Session hbSession = HibernateConnectionManager.getSessionFactory().openSession();
		this.getActiveSessions().add(hbSession);
		return hbSession;
	}
	
	public void closeSession(Session hbSession) {
		this.getActiveSessions().remove(hbSession);
	}
}
