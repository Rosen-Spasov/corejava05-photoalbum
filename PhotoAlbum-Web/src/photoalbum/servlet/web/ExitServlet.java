package photoalbum.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import photoalbum.entities.User;

/**
 * Servlet implementation class for Servlet: ExitServlet
 *
 */
 public class ExitServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("login", null);
		request.getRequestDispatcher("MainPage.jsp").forward(request, response);
	}   	  	    
}