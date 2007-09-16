package photoalbum.servlet.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;

import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.Category;
import photoalbum.entities.User;

/**
 * Servlet implementation class for Servlet: DeleteCategoryServlet
 *
 */
 public class DeleteCategoryServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		String category = (String)session.getAttribute("currentCategory");
		System.out.println(category);
		User user = (User)session.getAttribute("user");
		System.out.println(user);
		user.getCategories().remove(category);
		edit.updateUser(user);
		request.getRequestDispatcher("ShowUser.jsp").forward(request,response);
	}   	  	    
}