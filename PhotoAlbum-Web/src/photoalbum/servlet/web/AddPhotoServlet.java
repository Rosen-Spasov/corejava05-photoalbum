package photoalbum.servlet.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.Category;
import photoalbum.entities.User;

/**
 * Servlet implementation class for Servlet: AddPhotoServlet
 *
 */
 public class AddPhotoServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		User user = (User) session.getAttribute("user");
		String categoryName = (String)request.getParameter("cat");
		System.out.println(categoryName);
		Set<Category> allCategory = user.getCategories();
		String path = "";
		for (Category cat : allCategory) {
			if (cat.getCatName().equalsIgnoreCase(categoryName)) {
				path = edit.getAbsolutePath(cat);
				System.out.println(path);
				session.setAttribute("path", path);
			}
		}
		
		request.getRequestDispatcher("LargeUpload.jsp").forward(request, response);
	}   	  	    
}