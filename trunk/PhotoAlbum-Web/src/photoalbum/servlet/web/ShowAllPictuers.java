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
		int photoAtRow = 4;
		int photoColumn = 4;
		int photoAtPage = photoAtRow * photoColumn;
		session.setAttribute("photoAtRow", photoAtRow);
		session.setAttribute("photoAtPage", photoAtPage);
		session.setAttribute("owner", null);
		String cat = request.getParameter("param");
		session.setAttribute("currentCategory", cat);
		System.out.println(cat);
		int allPhotoCounter = 0;
			PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
			User user = (User)session.getAttribute("user");
	//		System.out.println(user.getUsername());
			Set<Category> allCategory = user.getCategories();
			String path;
			int count = 0;
			if (cat.equalsIgnoreCase("allPictures")){
				for (Category allCat : allCategory) {
					Set<Photo> allPhoto = allCat.getPhotos();
						allPhotoCounter += allPhoto.size();
					}
				String[] pathAll = new String[allPhotoCounter];
				String[] photoId = new String[allPhotoCounter];
				String[] photoName = new String[allPhotoCounter];
				String[] photoComment = new String[allPhotoCounter];
				
				int pa = 1 + allPhotoCounter / (photoAtPage);
				String pages = "" + pa;
	//			System.out.println(allPhotoCounter+" - "+ allPhotoCounter/photoAtPage);
				
				for (Category allCat : allCategory) {
					Set<Photo> allPhoto = allCat.getPhotos();
					for (Photo allPh : allPhoto) {
						photoName[count] = allPh.getPhName();
						photoId[count] =""+ allPh.getPhotoId();
						pathAll[count] = edit.getAbsolutePath(allPh);
						photoComment[count] =""+ allPh.getComments().size();
						count++;
					}
			}
			session.setAttribute("allPhotoCounter", allPhotoCounter);
			session.setAttribute("pages", pages);
			session.setAttribute("pathAllCurrent", pathAll);
			session.setAttribute("photoIdCurrent", photoId);
			session.setAttribute("photoNameCurrent", photoName);
			session.setAttribute("photoCommentCurrent", photoComment);
//			System.out.println("ob6to snimki ->"+ allPhotoCounter);
//			System.out.println("tova e "+cat);
		}else{
			allPhotoCounter=0;
			for (Category allCat : allCategory) {
				if (cat.equalsIgnoreCase(allCat.getCatName())){
					Set<Photo> photo = allCat.getPhotos();
					allPhotoCounter += photo.size();
				}
			}
			int pa = 1 + allPhotoCounter / (photoAtPage-1);
			String pages = "" + pa;
			
			String[] pathAll = new String[allPhotoCounter];
			String[] photoId = new String[allPhotoCounter];
			String[] photoName = new String[allPhotoCounter];
			String[] photoComment = new String[allPhotoCounter];
			String[] childCategories = null;
			for (Category allCat : allCategory) {
				if (cat.equalsIgnoreCase(allCat.getCatName())){
					Set<Photo> photo = allCat.getPhotos();
					for (Photo allPh : photo) {
						photoName[count] = allPh.getPhName();
						photoId[count] =""+ allPh.getPhotoId();
						pathAll[count] = edit.getAbsolutePath(allPh);
		//				System.out.println(edit.getAbsolutePath(allPh));
						photoComment[count] =""+ allPh.getComments().size();
						count++;
					}
					if (allCat.getCategories()!=null){
						int ch = 0;
						Set<Category> child = allCat.getCategories();
						childCategories = new String[child.size()];
						for (Category childCategory: child){
							childCategories[ch] = childCategory.getCatName();
							ch++;
						}
					}
				}
			}
//			System.out.println(allPhotoCounter+" - "+ allPhotoCounter/photoAtPage);
			
			session.setAttribute("allPhotoCounter", allPhotoCounter);
			session.setAttribute("pages", pages);
			session.setAttribute("curentCateg", cat);
			session.setAttribute("childCategories", childCategories);
			session.setAttribute("pathAllCurrent", pathAll);
			session.setAttribute("photoIdCurrent", photoId);
			session.setAttribute("photoNameCurrent", photoName);
			session.setAttribute("photoCommentCurrent", photoComment);
			
	//		System.out.println("ob6to snimki ->"+ allPhotoCounter);
	//		System.out.println("tova e "+cat);
		}
			
			
		request.getRequestDispatcher("ShowPageServlet").forward(request, response);
		
	}   	  	    
}