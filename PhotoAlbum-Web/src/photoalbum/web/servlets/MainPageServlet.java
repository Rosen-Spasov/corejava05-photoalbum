package photoalbum.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.entities.User;

/**
 * Servlet implementation class for Servlet: InitSessionServlet
 * This servlets initializes the session, i.e. sets the PAM and allUsers properties.
 */
 public class MainPageServlet extends BaseServlet {

	private static final long serialVersionUID = 367016231035980106L;
	
	public static final String ATTR_ALL_USERS = "allUsers";
	
	public static final String ATTR_PAM = BaseServlet.ATTR_PAM;
	
	public static final String ATTR_LOGGER = BaseServlet.ATTR_LOGGER;
	
	public static final String ATTR_INITIALIZED = BaseServlet.ATTR_INITIALIZED;
	
	public static final String REDIRECT = "mainPage.jsp";

	public MainPageServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		User[] allUsers = getPam().getAllUsers();
		getPam().refreshAll(allUsers);
		session.setAttribute(ATTR_ALL_USERS, allUsers);
		
		session.setAttribute(ATTR_INITIALIZED, true);
		
		response.sendRedirect(REDIRECT);
	}   	  	    
}