package photoalbum.servlet.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.Comment;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.hibernate.HibernateConnection;
import photoalbum.hibernate.HibernateConnectionManager;

/**
 * Servlet implementation class for Servlet: CommentServlet
 *
 */
 public class CommentServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CommentServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		String comment = (String)request.getParameter("comment");
		User userLogin = (User)session.getAttribute("login");
		User user = (User)session.getAttribute("user"); 
		Photo photo = (Photo)session.getAttribute("pic"); 
		
		System.out.println("user "+userLogin.getUsername()+"add comment "+comment+" to photo id " + photo.getPhotoId());
		edit.addComment(userLogin, photo, comment);
	
/*
 * tuk da dobawq update
 */
		request.getRequestDispatcher("ShowUser.jsp").forward(request, response);

		

	}   	  	    
}