package photoalbum.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.entities.Comment;
import photoalbum.entities.Photo;
import photoalbum.entities.User;

/**
 * Servlet implementation class for Servlet: CommentServlet
 *
 */
public class CommentServlet extends BaseServlet {

	private static final long serialVersionUID = 5123415305379266400L;
	
	public static final String PARAM_ACTION = "action";
	
	public static final String PARAM_TEXT = "text";
	
	public static final String PARAM_COMMENT_ID = "commentId";
	
	public static final String ATTR_SELECTED_PHOTO = PhotoServlet.ATTR_SELECTED_PHOTO;
	
	public static final String ATTR_SELECTED_USER = UserServlet.ATTR_SELECTED_USER;
	
	public static enum Action {ADD, DELETE}

	public CommentServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		try {
			String action = request.getParameter(PARAM_ACTION);
			if (action != null) {
				switch (Action.valueOf(action)) {
				case ADD:
					String text = request.getParameter(PARAM_TEXT);
					addComment(text);
					break;
				case DELETE:
					int commentId = Integer.parseInt(request.getParameter(PARAM_COMMENT_ID));
					deleteComment(commentId);
					break;
				default:
				}
			}
		} catch (NumberFormatException e) {
			getLogger().log(e);			
		} finally {
			response.sendRedirect("./photo?action=REFRESH");
		}
	}
	
	private void addComment(String text) {
		if (text == null) {
			return;
		}
		
		if (session.getAttribute(ATTR_SELECTED_PHOTO) != null && session.getAttribute(ATTR_SELECTED_USER) != null) {
			Photo selectedPhoto = (Photo) session.getAttribute(ATTR_SELECTED_PHOTO);
			User selectedUser = (User) session.getAttribute(ATTR_SELECTED_USER);
			
			if (selectedPhoto != null && selectedUser != null) {
				getPam().addComment(selectedUser, selectedPhoto, text);
			}
		}
	}
	
	private void deleteComment(int commentId) {
		Comment comment = getPam().getCommentById(commentId);
		if (comment != null) {
			getPam().deleteComment(comment);
		}
	}
}