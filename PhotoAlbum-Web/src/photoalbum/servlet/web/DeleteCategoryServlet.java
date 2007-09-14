package photoalbum.servlet.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;

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
		String category = (String)session.getAttribute("currentCategory");
		System.out.println(category);
		User user = (User)session.getAttribute("user");
		User emptyUser = new User();
		System.out.println(user);
		Set<Category> allCategory = user.getCategories();
		Set<Category> newCategory = emptyUser.getCategories();
		
		for (Category cat:allCategory){
			if (!cat.getCatName().equalsIgnoreCase(category)){
				newCategory.add(cat);
				System.out.println("not deleted :"+ cat.getCatName());
			}
		}
		user.setCategories(newCategory);
		/*
		 * i tuk update na user kym bazata
		 */
	}   	  	    
}