package photoalbum.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.entities.User;

/**
 * Servlet implementation class for Servlet: LoginServlet
 * This servlet is used to login and logout users.
 */
public class LoginServlet extends BaseServlet {

	private static final long serialVersionUID = -8979854711695109170L;
	
	public static final String PARAM_ACTION = "action";
	
	public static final String PARAM_USERNAME = RegistrationServlet.USERNAME;
	
	public static final String PARAM_PASSWORD = RegistrationServlet.PASSWORD;
	
	public static final String PARAM_LOGGED_USER = "loggedUser";
	
	public static final String ATTR_LOGGED_USER = PARAM_LOGGED_USER;
	
	public static final String LOGIN = "login";
	
	public static final String LOGOUT = "logout";

	public LoginServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String action = request.getParameter(PARAM_ACTION);
		if (LOGIN.equals(action)) {
			String username = request.getParameter(PARAM_USERNAME);
			String password = request.getParameter(PARAM_PASSWORD);
			if (getPam().accessGranted(username, password)) {
				User loggedUser = getPam().getUserByUsername(username);
				session.setAttribute(PARAM_LOGGED_USER, loggedUser);
			}
		} else if (LOGOUT.equals(action)) {
			session.removeAttribute(PARAM_LOGGED_USER);
		}
		
		response.sendRedirect("./mainPage.jsp?refresh=true");
	}   	  	    
}