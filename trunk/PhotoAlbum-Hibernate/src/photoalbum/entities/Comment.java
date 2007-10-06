package photoalbum.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment implements Serializable, Comparable<Comment> {

	private static final long serialVersionUID = 2989246789276241609L;

	private int commentId;

	private String text;

	private Photo photo;

	private User user;
	
	private Date commentDate;
	
	private String dateAsString;
	
	public String getDateAsString() {
		if (dateAsString == null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateAsString = dateFormat.format(getCommentDate());
		}
		return dateAsString;
	}
	
	public void setDateAsString(String dateAsString) {
		this.dateAsString = dateAsString;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

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
	
	public Comment(User user, Photo photo, String text) {
		this(user, photo, text, new Date());
	}
	
	public Comment(User user, Photo photo, String text, Date commentDate) {
		this.setText(text);
		this.setPhoto(photo);
		this.setUser(user);
		this.setCommentDate(commentDate);
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
						this.getUser().equals(comment.getUser()) &&
						this.getCommentDate().equals(comment.getCommentDate());
		}
		return result;
	}

	public int compareTo(Comment comment) {
		return this.getText().compareToIgnoreCase(comment.getText());
	}

}
