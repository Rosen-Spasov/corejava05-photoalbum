package photoalbum.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.User;

/**
 * Servlet implementation class for Servlet: SearchServlet
 * 
 */
public class SearchServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		String searchName = (String)request.getParameter("searchName");
		String category = (String)request.getParameter("category");
		System.out.println(searchName + category);
		if (searchName.length()>0) {
			
			User user = edit.getUserByUsername(searchName);
			session.setAttribute("user", user);
			
				if (category != null) {
					boolean cat = user.getCategories().contains(category);
					session.setAttribute("category", cat);
					request.getRequestDispatcher("ShowUser.jsp").forward(
							request, response);
				} else {
					
					session.setAttribute("category", null);
					request.getRequestDispatcher("ShowUser.jsp").forward(
							request, response);
				}
		} else {
			request.setAttribute("search", "enter user name");
			request.getRequestDispatcher("MainPage.jsp").forward(request,
					response);
		}
	}
}
