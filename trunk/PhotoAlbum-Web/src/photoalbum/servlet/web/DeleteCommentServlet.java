package photoalbum.servlet.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;

import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.Comment;
import photoalbum.entities.User;

/**
 * Servlet implementation class for Servlet: DeleteCommentServlet
 *
 */
 public class DeleteCommentServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DeleteCommentServlet() {
		super();
	}   	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		Integer deleteComment = Integer.parseInt(request.getParameter("comm").toString());
		System.out.println(deleteComment);
		User userLogin = (User)session.getAttribute("login");
		System.out.println(userLogin);
		Set<Comment> allComment =  userLogin.getComments();
		System.out.println(allComment.size());
		for (Comment comm : allComment){
			System.out.println(comm.getCommentId());
			if (comm.getCommentId() == deleteComment){
				System.out.println(comm.getCommentId());
				userLogin.getComments().remove(comm);
			}
		}
		edit.updateUser(userLogin);
		session.setAttribute("login", userLogin);
		request.getRequestDispatcher("MainPage.jsp").forward(request, response);
	}   	  	    
}