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
import photoalbum.entities.User;

/**
 * Servlet implementation class for Servlet: ShowAllPictuers
 *
 */
 public class ShowAllPictuers extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    	
	
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
		String cat = request.getParameter("param");
		int allPhotoCounter = 0;
			PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
			User user = (User)session.getAttribute("user");
			System.out.println(user.getUsername());
			Set<Category> allCategory = user.getCategories();
			String path;
			int count = 0;
			if (cat.equalsIgnoreCase("allPictures")){
				for (Category allCat : allCategory) {
					Set<Photo> allPhoto = allCat.getPhotos();
					for (Photo allPh : allPhoto) {
						allPhotoCounter++;
					}
				}
				String[] pathAll = new String[allPhotoCounter];
				String[] photoId = new String[allPhotoCounter];
				String[] photoName = new String[allPhotoCounter];
				String[] photoComment = new String[allPhotoCounter];
				int pa = 1 + allPhotoCounter / 7;
				String pages = "" + pa;
				for (Category allCat : allCategory) {
					Set<Photo> allPhoto = allCat.getPhotos();
					for (Photo allPh : allPhoto) {
						photoName[count] = allPh.getPhName();
						photoId[count] =""+ allPh.getPhotoId();
						pathAll[count] = allPh.getPath();
						photoComment[count] =""+ allPh.getComments().size();
						count++;
					}
			}
			session.setAttribute("pathAll", pathAll);
			session.setAttribute("photoId", photoId);
			session.setAttribute("photoName", photoName);
			session.setAttribute("photoComment", photoComment);
			System.out.println("ob6to snimki ->"+ allPhotoCounter);
			System.out.println("tova e "+cat);
		}else{
			allPhotoCounter=0;
			for (Category allCat : allCategory) {
				if (cat.equalsIgnoreCase(allCat.getCatName())){
					Set<Photo> photo = allCat.getPhotos();
					for (Photo allPh : photo) {
						allPhotoCounter++;
						
					}
				}
			}
			String[] pathAll = new String[allPhotoCounter];
			String[] photoId = new String[allPhotoCounter];
			String[] photoName = new String[allPhotoCounter];
			String[] photoComment = new String[allPhotoCounter];
			for (Category allCat : allCategory) {
				if (cat.equalsIgnoreCase(allCat.getCatName())){
					Set<Photo> photo = allCat.getPhotos();
					for (Photo allPh : photo) {
						photoName[count] = allPh.getPhName();
						photoId[count] =""+ allPh.getPhotoId();
						pathAll[count] = allPh.getPath();
						photoComment[count] =""+ allPh.getComments().size();
						count++;
					}
				}
			}
			session.setAttribute("pathAll", pathAll);
			session.setAttribute("photoId", photoId);
			session.setAttribute("photoName", photoName);
			session.setAttribute("photoComment", photoComment);
			System.out.println("ob6to snimki ->"+ allPhotoCounter);
			System.out.println("tova e "+cat);
		}
			
			
		request.getRequestDispatcher("ShowPageServlet").forward(request, response);
		
	}   	  	    
}