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
		Session hbSession = (Session) HibernateConnectionManager.openConnection();
		System.out.println("tuk sme");
		String userName = request.getParameter("username");
		String pass = request.getParameter("pass");
		if (isValid(userName,pass,request)){
		System.out.println(userName+" "+ pass);
		if (userExist(userName,pass,hbSession)){
			session.setAttribute("login", "true");
			request.getRequestDispatcher("MainPage.jsp").forward(request, response);
		}else{
			String[] errors = new String[5];
			errors[0] = "you are not registered";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("MainPage.jsp").forward(request, response);
			
		}}else {
			request.getRequestDispatcher("MainPage.jsp").forward(request, response);
		}
	}

	private boolean userExist(String userName, String pass, Session hbSession) {
		
		HibernateConnection hc = new HibernateConnection(hbSession);
		User user = hc.getUserByUserName(userName);
		if (user.getPassword().equalsIgnoreCase(pass)){
			return true;
		}
		return false;
	}

	private boolean isValid(String userName, String pass, HttpServletRequest request) {
	
		boolean result=true;
		String[] errors = new String[5];
		int errorsCount = 0;
		if (userName.length()>0){
			
		}else{
			errors[errorsCount]="enter a valid name";
			errorsCount++;
			result = false;
		}
		if (pass.length()>0){
			
		}else{
			errors[errorsCount]="enter password";
			errorsCount++;
			result = false;
		}
		request.setAttribute("errors", errors);
		return result;
	}   	  	    
}