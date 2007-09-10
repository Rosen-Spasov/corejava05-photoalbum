package photoalbum.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.User;




/**
 * Servlet implementation class for Servlet: LoginServlet
 *
 */
 public class LoginServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	 
	 /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		String userName = request.getParameter("username");
		String pass = request.getParameter("pass");
		String[] errors = new String[5];
		if (isValid(userName,pass,request)){
		System.out.println(userName+" "+ pass);
		User user = edit.getUserByUsername(userName);
			if (user != null){
				if (user.getPassword().equalsIgnoreCase(pass)){
				session.setAttribute("login", user);
				request.getRequestDispatcher("MainPage.jsp").forward(request, response);
				}else{
					errors[0] = "wrong user name or password";
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("MainPage.jsp").forward(request, response);
				}
			}else{
				session.setAttribute("login", null);
				errors[0] = "you are not registered";
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("MainPage.jsp").forward(request, response);
			
		}}else {
			session.setAttribute("login", null);
			request.getRequestDispatcher("MainPage.jsp").forward(request, response);
		}
	}

	private boolean isValid(String userName, String pass, HttpServletRequest request) {
	
		boolean result=true;
		String[] errors = new String[5];
		int errorsCount = 0;
		if (!(userName.length()>0)){
			errors[errorsCount]="enter a valid name";
			errorsCount++;
			result = false;
		}
		if (!(pass.length()>0)){
			errors[errorsCount]="enter password";
			errorsCount++;
			result = false;
		}
		request.setAttribute("errors", errors);
		return result;
	}   	  	    
}