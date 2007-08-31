package hibernate.queries;

import hibernate.utils.HibernateConnection;
import hibernate.utils.HibernateConnectionManager;

import java.util.List;

import logging.Logger;

import org.hibernate.Query;

import entities.Category;

public class CategoryQuery {
	
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
	public List<Category> getAllCategorys() {
		List<Category> result = null;
		
		String hql = "from Category";
		Query query = this.openHbConnection().createQuery(hql);
		result = (List<Category>) query.list();
		this.closeHbConnection();
		
		return result;
	}
	
	
	public Category getCategoryByID(int categoryID) {
		Category result = (Category) this.openHbConnection().get(Category.class, categoryID);
		this.closeHbConnection();
		return result;
	}
	
	public Category getCategoryByCategoryName(String catName) {
		Category category = null;
		
		String hql = "from Category c where c.CatName=:categoryname";
		Query query = this.openHbConnection().createQuery(hql);
		query.setString("categoryName", catName);
		List list = (List) query.list();
		if (list != null && list.size() != 1) {
			try {
				category = (Category) list.get(0);
			} catch (ClassCastException exc) {
				this.logger.log(exc);
			}
		} else {
			this.getLogger().log("ERROR: Cannot find Category with name [" + catName + "] or there is more than one Category with the given name.");
		}
		this.closeHbConnection();
		
		return category;
	}

}
