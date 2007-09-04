package photoalbum.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import photoalbum.entities.User;
import photoalbum.hibernate.utils.HibernateConnection;
import photoalbum.hibernate.utils.HibernateConnectionManager;

/**
 * Servlet implementation class for Servlet: SearchServlet
 *
 */
 public class SearchServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Session hbSession = (Session) HibernateConnectionManager.openConnection();
		User user = new User();
		String name =(String)request.getParameter("searchName");
		String category = (String)request.getParameter("category");
		System.out.println(name + category);
		HibernateConnection hc = new HibernateConnection(hbSession);
		user = hc.getUserByUserName(name);
		session.setAttribute("user", user);
		session.setAttribute("category", category);
		request.getRequestDispatcher("ShowUser.jsp").forward(request, response);
		
//		int id = 2;
//		String url = "ShowUser.jsp?param1="+id;
//		request.getRequestDispatcher(url).forward(request, response);
	}   	  	    
}
 
