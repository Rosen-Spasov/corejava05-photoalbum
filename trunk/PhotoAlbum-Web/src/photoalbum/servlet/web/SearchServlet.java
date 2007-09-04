package photoalbum.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: SearchServlet
 *
 */
 public class SearchServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name =(String)request.getParameter("searchName");
		String category = (String)request.getParameter("category");
		System.out.println(name + category);
		int id = 2;
		String url = "ShowUser.jsp?param1="+id;
		request.getRequestDispatcher(url).forward(request, response);
	}   	  	    
}
 
