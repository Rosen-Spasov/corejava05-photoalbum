package entities;

import java.util.HashSet;
import java.util.Set;

public class Category {

	private int categoryId;

	private String catName;

	private String path;

	private Category childCategory;

	private User user;

	private Set<Photo> photos = new HashSet<Photo>();

	private Set<Category> categories = new HashSet<Category>();

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
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

	public Category getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(Category childCategory) {
		this.childCategory = childCategory;
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
	
	public String toString() {
		return this.getCatName();
	}
	
	public void addCategory(Category category) {
		this.getCategories().add(category);
	}
	
	public void addPhoto(Photo photo) {
		this.getPhotos().add(photo);
	}

}
