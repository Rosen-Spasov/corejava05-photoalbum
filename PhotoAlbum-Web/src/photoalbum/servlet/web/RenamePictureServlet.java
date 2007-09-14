package photoalbum.servlet.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.Category;
import photoalbum.entities.Photo;



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
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		Category category = (Category)session.getAttribute("category");
		Photo photo = (Photo)session.getAttribute("photo");
		String[] raz = photo.getPhName().split("[.]");
		
		String newName = (String)request.getParameter("newName");
		newName = newName+"."+raz[1];
		System.out.println(photo.getPhName()+ " " + newName + " " + category);
		String[] errors = new String[5];
		if (validate(newName,request,errors)){
			Set<Photo> allPhoto =  category.getPhotos();
			for (Photo all:allPhoto){
				if (all.getPhName().equalsIgnoreCase(photo.getPhName())){
					errors[3] = "towa ime sy6testwuwa w tazi kategoria";
				}else{
					photo.setPhName(newName);
					/*
					 * i sega update na producta
					 */
					// edit.update;
				}
			}
			System.out.println("rename");
			request.getRequestDispatcher("ShowUser.jsp").forward(request, response);
		}else{
			System.out.println(errors.length);
			System.out.println(photo.getPhotoId());
			session.setAttribute("pic", photo.getPhotoId());
			session.setAttribute("errors", errors);
			request.getRequestDispatcher("renamePicture.jsp?pic="+photo.getPhotoId()).forward(request, response);
			
		}
	}

	private boolean validate(String newName, HttpServletRequest request, String[] errors) {
		
		System.out.println(newName);
		boolean result = true;
		if (newName.length()>2){
			/*
			 * moje da ima prowerka dali sy6testwuwa towa ime
			 */
			
		}else{
			errors[1] = "Enter name";
			result = false;
		}
		if (newName.contains("[/.,!?]")){
			errors[2] = "ne moje da sydyrja /.,!?";
			result = false;
		}
	
		
		return result;
	}   	      
}