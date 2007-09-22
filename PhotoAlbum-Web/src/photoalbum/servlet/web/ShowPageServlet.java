package photoalbum.servlet.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;

import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;


/**
 * Servlet implementation class for Servlet: ShowPageServlet
 *
 */
 public class ShowPageServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	public ShowPageServlet() {
		super();
	}   	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int photoAtRow = 4;
		int photoColumn = 4;
		int photosAtPage = photoAtRow * photoColumn;
		int allPhotoCounter = 0;
		int now = 0;
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		String category = null;
		
		if (request.getParameter("category")!=null && !(request.getParameter("category").equalsIgnoreCase("null"))){
			category = (String)request.getParameter("category");
			System.out.println("abe ne e null " + request.getParameter("category"));
		}else{
			session.setAttribute("categorySearch", null);
		}
		Category categorySearch = (Category) session.getAttribute("categorySearch");
		User user = (User)session.getAttribute("userSearch");
		Set<Photo> allPhoto = null;
		 	Set<Category> allCategory = user.getCategories();
			for (Category allCat : allCategory) {
				if (allCat.getCatName().equalsIgnoreCase(category)){
					categorySearch = allCat;
					session.setAttribute("categorySearch", categorySearch);
				}
				allPhoto = allCat.getPhotos();
				allPhotoCounter += allPhoto.size();
			}
			if (categorySearch!=null){
				allPhoto = categorySearch.getPhotos();
				allPhotoCounter = allPhoto.size();
			}
		String[] pathAll = new String[photosAtPage];
		String[] photoId = new String[photosAtPage];
		String[] photoName = new String[photosAtPage];
		String[] photoComment = new String[photosAtPage];
		String[] pathAllFirst = new String[allPhotoCounter];
		String[] photoIdFirst = new String[allPhotoCounter];
		String[] photoNameFirst = new String[allPhotoCounter];
		String[] photoCommentFirst = new String[allPhotoCounter];
		int pa = 1 + allPhotoCounter / photosAtPage;
		String pages = "" + pa;
		int count = 0;
		
		if (categorySearch == null){
			System.out.println("abe null e");
		for (Category allCat : allCategory) {
				allPhoto = allCat.getPhotos();
				for (Photo allPh : allPhoto) {
					pathAllFirst[count] = edit.getAbsolutePath(allPh);
					photoIdFirst[count] = "" + allPh.getPhotoId();
					photoNameFirst[count] = allPh.getPhName();
					photoCommentFirst[count] = "" + allPh.getComments().size();
//					System.out.println(allPh.getPhName());
					count++;
				}
			}
		}else{
			allPhoto = categorySearch.getPhotos();
			for (Photo allPh : allPhoto) {
				pathAllFirst[count] = edit.getAbsolutePath(allPh);
				photoIdFirst[count] = "" + allPh.getPhotoId();
				photoNameFirst[count] = allPh.getPhName();
				photoCommentFirst[count] = "" + allPh.getComments().size();
//				System.out.println(allPh.getPhName());
				count++;
			}
		}
//		System.out.println(request.getParameter("page"));
//		System.out.println(pa);
		int pageCurrent = 1;
		if (request.getParameter("page") != null){
			pageCurrent = Integer.parseInt(request.getParameter("page"));
		}
		if (pageCurrent>pa || pageCurrent<1){
			pageCurrent=1;
		}
		now=0;
		System.out.println("teku6ta stranica :"+pageCurrent+" ob6to snimki "+ allPhotoCounter);
		int begin = pageCurrent*photosAtPage - photosAtPage;
//		System.out.println(begin);
		for (int k = begin; k < begin+photosAtPage;k++){
			if (k < allPhotoCounter) {
				pathAll[now] = pathAllFirst[k];
				photoId[now] = photoIdFirst[k];
				photoName[now] = photoNameFirst[k];
				photoComment[now] = photoCommentFirst[k];
				now++;
			} 
//			System.out.println(now);
		}
		
		System.out.println(category);
		session.setAttribute("photoAtRow", photoAtRow);
		session.setAttribute("photoAtPage", photosAtPage);
		session.setAttribute("owner", null);
		session.setAttribute("categoryToView", categorySearch);
		session.setAttribute("nowPage", pageCurrent);
		session.setAttribute("allPhotoCounter", allPhotoCounter);
		session.setAttribute("allPages", pages);
		session.setAttribute("pathAll", pathAll);
		session.setAttribute("photoId", photoId);
		session.setAttribute("photoName", photoName);
		session.setAttribute("photoComment", photoComment);
	
		
		request.getRequestDispatcher("ShowUser.jsp").forward(request, response);
	}   	  	    
}