package photoalbum.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class for Servlet: RenamePictureServlet
 *
 */
 public class RenamePictureServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public RenamePictureServlet() {
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
		String oldName = (String)session.getAttribute("pic");
		String newName = (String)request.getParameter("newName");
		System.out.println(oldName+ " " + newName);
		System.out.println(newName.length());
		if (validate(newName,request)){
			System.out.println("rename");
		}else{
		
			request.getRequestDispatcher("renamePicture.jsp").forward(request, response);
			
		}
	}

	private boolean validate(String newName, HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(newName);
		boolean result = true;
		String err ="";
		if (newName.length()>2){
			/*
			 * moje da ima prowerka dali sy6testwuwa towa ime
			 */
			
		}else{
			err = "Enter name";
			result = false;
		}
		if (newName.contains("[/.,!?]")){
			err += "ne moje da sydyrja /.,!?";
			result = false;
		}
		if (result){
			err += "uspe6no";
		}
		session.setAttribute("err", err);
		return result;
	}   	      
}