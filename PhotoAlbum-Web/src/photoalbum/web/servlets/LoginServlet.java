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
	
	public static final String PARAM_OPTION = "option";
	
	public static final String ATTR_USERNAME = RegistrationServlet.USERNAME;
	
	public static final String ATTR_PASSWORD = RegistrationServlet.PASSWORD;
	
	public static final String ATTR_LOGGED_USER = "loggedUser";
	
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

		if (initialized) {
			String option = (String) request.getParameter(PARAM_OPTION);
			if (LOGIN.equals(option)) {
				String username = (String) request.getAttribute(ATTR_USERNAME);
				String password = (String) request.getAttribute(ATTR_PASSWORD);
				if (pam.accessGranted(username, password)) {
					User loggedUser = pam.getUserByUsername(username);
					session.setAttribute(ATTR_LOGGED_USER, loggedUser);
				}
			} else if (LOGOUT.equals(option)) {
				session.removeAttribute(ATTR_LOGGED_USER);
			}
		}
		
		response.sendRedirect("mainPage.jsp");
	}   	  	    
}