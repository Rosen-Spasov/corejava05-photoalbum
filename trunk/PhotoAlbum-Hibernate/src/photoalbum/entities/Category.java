package photoalbum.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Category implements Serializable {

	private static final long serialVersionUID = 591870800372000848L;

	private int categoryId;

	private String catName;

	private String path;

	private Category parentCategory;

	private User user;

	private Set<Photo> photos = new HashSet<Photo>();

	private Set<Category> childCategories = new HashSet<Category>();

	public Set<Category> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Set<Category> childCategories) {
		this.childCategories = childCategories;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Category() {
	}
	
	public Category(String catName, String path) {
		this(catName, path, null, null);
	}
	
	public Category(String catName, String path, User user) {
		this(catName, path, user, null);
	}
	
	public Category(String catName, String path, User user, Category parentCategory) {
		this.setCatName(catName);
		this.setPath(path);
		this.setUser(user);
		this.setParentCategory(parentCategory);
	}
	
	public String toString() {
		return this.getCatName();
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Category) {
			Category category = (Category) obj;
			result = this.getPath().equals(category.getPath());
		}
		return result;
	}

}
