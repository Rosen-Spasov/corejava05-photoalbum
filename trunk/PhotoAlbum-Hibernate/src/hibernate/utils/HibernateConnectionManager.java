package hibernate.utils;

import java.util.ArrayList;

import logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnectionManager {
	
	public static final String CONFIG_FILE = "/hibernate.cfg.xml";
	
	public static int CONNECTION_POOL_SIZE = 20;
	
	static {
		for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
			openConnection();
		}
	}
	
	private static SessionFactory sessionFactory;
	
	private static ArrayList<HibernateConnection> connections = null;
	
	private static SessionFactory getSessionFactory() {
		if (HibernateConnectionManager.sessionFactory == null) {
			Configuration cfg = new Configuration();
			cfg.configure(CONFIG_FILE);
			HibernateConnectionManager.sessionFactory = cfg.buildSessionFactory(); 
		}
		return HibernateConnectionManager.sessionFactory;
	}
	
	private static ArrayList<HibernateConnection> getConnections() {
		if (connections == null) {
			connections = new ArrayList<HibernateConnection>();
		}
		return connections;
	}
	
	private HibernateConnectionManager() {
	}
	
	private static HibernateConnection findAvailableConnection() {
		HibernateConnection connection = null;
		for (int index = 0; index < getConnections().size(); index++) {
			if (getConnections().get(index).isAvailable()) {
				connection = getConnections().get(index);
				break;
			}
		}
		return connection;
	}
	
	public static synchronized HibernateConnection openConnection() {
		HibernateConnection connection = findAvailableConnection();
		if (connection == null) {
			Session session = HibernateConnectionManager.getSessionFactory().openSession();
			try {
				connection = new HibernateConnection(session);
			} catch (IllegalArgumentException exc) {
				Logger.getDefaultInstance().log(exc);
			}
			getConnections().add(connection);
		}
		return connection;
	}
	
	public static synchronized void closeConnection(HibernateConnection connection) {
		if (getConnections().size() > CONNECTION_POOL_SIZE) {
			getConnections().remove(connection);
		}
	}
	
}
