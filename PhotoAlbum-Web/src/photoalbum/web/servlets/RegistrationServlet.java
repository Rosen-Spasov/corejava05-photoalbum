package photoalbum.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.CreateUserException;

/**
 * Servlet implementation class for Servlet: BaseServlet
 * This servlet is used to register new users new users in the system.
 */
public class RegistrationServlet extends BaseServlet {

	private static final long serialVersionUID = -7322500585952234313L;
	
	public static final String USERNAME = "username";
	
	public static final String PASSWORD = "password";
	
	public static final String PASSWORD_CONFIRM = "passwordConfirm";
	
	public static final String FIRST_NAME = "firstName";
	
	public static final String LAST_NAME = "lastName";

	public RegistrationServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		if (initialized) {
			String username = request.getParameter(USERNAME);
			String password = request.getParameter(PASSWORD);
			String passwordConfirm = request.getParameter(PASSWORD_CONFIRM);
			String firstName = request.getParameter(FIRST_NAME);
			String lastName = request.getParameter(LAST_NAME);
			if (password != null && password.equals(passwordConfirm)) {
				try {
					pam.addUser(username, password, firstName, lastName);
					request.getRequestDispatcher("login?action=login").forward(request, response);
				} catch (CreateUserException e) {
					logger.log(e);
				}
			}
		}
	}   	  	    
}