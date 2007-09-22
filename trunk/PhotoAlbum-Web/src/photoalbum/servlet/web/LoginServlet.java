package photoalbum.servlet.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		if (isValid(userName,pass,request,errors)){
//		System.out.println(userName+" "+ pass);
		User user = edit.getUserByUsername(userName);
			if (user != null){
//				System.out.println(user.getUsername());
				if (user.getPassword().equalsIgnoreCase(pass)){
				session.setAttribute("login", user);
				request.getRequestDispatcher("MainPage.jsp").forward(request, response);
				}else{
					errors[0] = "wrong user name or password";
	//				System.out.println("wrong user name or password");
					
					request.getRequestDispatcher("MainPage.jsp").forward(request, response);
				}
			}else{
			
				session.setAttribute("login", null);
				errors[0] = "you are not registered";
//				System.out.println(errors[0]);
				
				request.getRequestDispatcher("MainPage.jsp").forward(request, response);
			
		}}else {
//			System.out.println("no logs");
			
//			System.out.println(errors[0]);
			
			session.setAttribute("login", null);
			request.getRequestDispatcher("MainPage.jsp").forward(request, response);
		}
		for (int k=0;k<errors.length;k++){
//		System.out.println("loops->"+errors[k]);
		}
		session.setAttribute("errors", errors);
	}

	private boolean isValid(String userName, String pass, HttpServletRequest request, String[] errors) {
	
		boolean result=true;
		
		if (!(userName.length()>0)){
			errors[1]="enter a valid name";
			
			result = false;
		}
		if (!(pass.length()>0)){
			errors[2]="enter password";
			
			result = false;
		}
                String target = "(([0-9]+)?([A-Za-z]+)?)(([0-9]+)?([A-Za-z]+)?)*";
                if (!userName.matches(target))	{
			errors[3]="enter a valid name";
                        result = false;
		}

                if (!pass.matches(target))	{
			errors[4]="enter a valid password";
                        result = false;
		}	  	
                
		
		return result;
	}   	  	    
}