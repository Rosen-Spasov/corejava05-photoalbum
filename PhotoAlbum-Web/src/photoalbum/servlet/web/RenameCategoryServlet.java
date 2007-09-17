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
 * Servlet implementation class for Servlet: RenameCategoryServlet
 *
 */
 public class RenameCategoryServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		String category = (String)session.getAttribute("currentCategory");
		String newCategoryName = (String)request.getParameter("newCategoryName");
		String[] errorsCat = new String[3];
		User user = (User)session.getAttribute("user");
		Set<Category> allCategory = user.getCategories();
		
		if (valid(newCategoryName,errorsCat,allCategory)){
		
		for (Category cat : allCategory){
			if (cat.getCatName().equalsIgnoreCase(category)){
				
				edit.renameCategory(cat, newCategoryName);
				System.out.println("Category ->"+category+"new name ->"+newCategoryName);
			}
		}
		session.setAttribute("photoAtPage",null);
		User refr = edit.getUserById(user.getUserId());
		session.setAttribute("user", refr);
		request.getRequestDispatcher("ShowUser.jsp").forward(request, response);
		
		/*
		 * update
		 */
		}else{
			session.setAttribute("errorsCat", errorsCat);
			request.getRequestDispatcher("renameCategory.jsp").forward(request, response);
		}
	}

	private boolean valid(String newCategoryName, String[] errorsCat, Set<Category> allCategory) {
		boolean result = true;
		if (newCategoryName.length()>2){
			for (Category cat : allCategory){
				if (cat.getCatName().equalsIgnoreCase(newCategoryName)){
					errorsCat[1] = "kategoriq s towa ime we4e sy6testwuwa";
					result = false;
				}
			}
		}else{
			errorsCat[1] = "Enter name";
			result = false;
		}
		if (newCategoryName.contains("[/.,!?]")){
			errorsCat[2] = "ne moje da sydyrja /.,!?";
			result = false;
		}
	
		
		return result;
	
	}   	  	    
}