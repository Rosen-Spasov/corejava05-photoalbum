package photoalbum.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Photo implements Serializable, Comparable<Photo> {

	private static final long serialVersionUID = -876226176760185873L;

	private int photoId;

	private String phName;

	private String path;

	private Category category;

	private Set<Comment> comments = new HashSet<Comment>();

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getPhName() {
		return phName;
	}

	public void setPhName(String phName) {
		this.phName = phName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	
	public Photo() {
	}
	
	public Photo(String phName, String path, Category category) {
		this.setPhName(phName);
		this.setPath(path);
		this.setCategory(category);
	}
	
	public String toString() {
		return this.getPhName();
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Photo) {
			Photo photo = (Photo) obj;
			result = this.getPath().equals(photo.getPath());
		}
		return result;
	}

	public int compareTo(Photo photo) {
		return this.getPath().compareTo(photo.getPath());
	}
	
}
