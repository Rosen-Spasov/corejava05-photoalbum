package photoalbum.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;

import com.ibm.CORBA.iiop.Request;
import com.ibm.xml.b2b.util.RewindableInputStream;

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
		System.out.println("tuk sme");
		String userName = request.getParameter("username");
		String pass = request.getParameter("pass");
		if (isValid(userName,pass,request)){
		System.out.println(userName+" "+ pass);
		if (userExist()){
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

	private boolean userExist() {
		/*
		 * tuk trqbwa da se prowerqwa dali ima takyw user i da izwede syotwetno syob6tenie
		 * ako ne
		 */
		return true;
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