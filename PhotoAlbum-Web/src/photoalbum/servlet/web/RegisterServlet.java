package photoalbum.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import photoalbum.CreateUserException;
import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.User;


/**
 * Servlet implementation class for Servlet: RegisterServlet
 *
 */
 public class RegisterServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] errorsRegister = new String[7];
		User newUser = new User();
		if (isValid(request,newUser,errorsRegister)){
			
			request.getRequestDispatcher("MainPage.jsp").forward(request, response);
			
		}else{
			session.setAttribute("newUser", newUser);
			session.setAttribute("errorsRegistration", errorsRegister);
			request.getRequestDispatcher("register.jsp").forward(request, response);
	}
	}

	private boolean isValid(HttpServletRequest request, User newUser, String[] errors) {
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		
		String fName = (String)request.getParameter("fName");
		String lName = (String)request.getParameter("lName");
		String uName = (String)request.getParameter("uName");
		String pass = (String)request.getParameter("pass");
		String confPass = (String)request.getParameter("confPass");
		
		
		boolean result = true;
		if (fName.length()>0){
			newUser.setFirstName(fName);
		
		}else{
			errors[1]="enter first name";
			
			result = false;
		}
		if (lName.length()>0){
			newUser.setLastName(lName);
		}else{
			errors[2]="enter last name";
			
			result = false;
		}
		if (uName.length()>0){
	
			if (!false){
				newUser.setUsername(uName);
			}else{
				errors[3]="user name already exist";
				
				result = false;
			}}else{
			errors[3]="enter user name";
			result = false;
		}
		if (pass.length()>0){
			newUser.setPassword(pass);
		}else{
			errors[4]="enter password";
			result = false;
		}
		if (confPass.length()>0){
			/*
			 * tuk se setwa kym bina na hibernate
			 */
		}else{
			errors[5]="confirm password";
			result = false;
		}
		if (confPass.equalsIgnoreCase(pass)){
			/*
			 * tuk se setwa kym bina na hibernate
			 */
		}else{
			errors[6]="repeat password corect";
			result = false;
		}
		
		
		if (result){
			errors[0]="successful registration";
			try {
				edit.addUser(newUser);
			} catch (CreateUserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return result;
	}   	  	    
}