package photoalbum.entities;

import java.io.Serializable;

public class Comment implements Serializable {

	private static final long serialVersionUID = 2989246789276241609L;

	private int commentId;

	private String text;

	private Photo photo;

	private User user;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Comment() {
	}
	
	public Comment(String text, Photo photo, User user) {
		this.setText(text);
		this.setPhoto(photo);
		this.setUser(user);
	}
	
	public String toString() {
		return this.getText();
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Comment) {
			Comment comment = (Comment) obj;
			result = this.getText().equals(comment.getText()) &&
						this.getPhoto().equals(comment.getPhoto()) &&
						this.getUser().equals(comment.getUser());
		}
		return result;
	}

}
