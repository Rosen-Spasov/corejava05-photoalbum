package photoalbum.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {

	private static final long serialVersionUID = 3756410723326541585L;

	private int userId;

	private String username;

	private String firstName;

	private String lastName;

	private String password;

	private Set<Category> categories = new HashSet<Category>();

	private Set<Comment> comments = new HashSet<Comment>();

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public User() {		
	}
	
	public User(String username, String password, String firstName, String lastName) {
		this.setUsername(username);
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
	
	public String toString() {
		return this.getUsername();
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof User) {
			User user = (User) obj;
			result = user.getUsername().equals(user.getUsername());
		}
		return result;
	}

}
