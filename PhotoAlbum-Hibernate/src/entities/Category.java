package entities;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private int categoryId;

	private String name;

	private String path;

	private Category childCategory;

	private User user;

	private List<Photo> photos = new ArrayList<Photo>();

	private List<Category> categories = new ArrayList<Category>();

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
