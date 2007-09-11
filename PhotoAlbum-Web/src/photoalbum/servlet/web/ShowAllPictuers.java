package photoalbum.servlet.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import photoalbum.entities.Category;
import photoalbum.entities.User;

/**
 * Servlet implementation class for Servlet: ShowAllPictuers
 *
 */
 public class ShowAllPictuers extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    	
	
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
		String cat = request.getParameter("param");
		String allPictures = request.getParameter("allPictures");
		System.out.println(allPictures);
		if (allPictures != null){
			session.setAttribute("allPictures", "allPictures");
			request.getRequestDispatcher("ShowUser.jsp").forward(request, response);
		}
		User user = (User)session.getAttribute("user");
		System.out.println(cat);
		Set<Category> category = user.getCategories();
		
		for (Category c: category){
			System.out.println(c.getCatName());
			if (c.getCatName().equalsIgnoreCase(cat)){
				String path = c.getPath();
				session.setAttribute("allPictures", null);
				session.setAttribute("path", c);
				request.getRequestDispatcher("ShowUser.jsp").forward(request, response);
			}
		}
	}   	  	    
}