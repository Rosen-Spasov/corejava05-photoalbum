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
 * Servlet implementation class for Servlet: PageServlet
 * 
 */
public class PageServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	public PageServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int allPhotoCounter = 0;
		int now = 0;
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		User[] allUser = edit.getAllUsers();
		for (User userAll : allUser) {
			Set<Category> allCategory = userAll.getCategories();
			for (Category allCat : allCategory) {
				Set<Photo> allPhoto = allCat.getPhotos();
				allPhotoCounter += allPhoto.size();
			}
		}

		
		String[] pathAll = new String[6];
		String[] photoId = new String[6];
		String[] photoName = new String[6];
		String[] photoComment = new String[6];
		
		String[] pathAllFirst = new String[allPhotoCounter];
		String[] photoIdFirst = new String[allPhotoCounter];
		String[] photoNameFirst = new String[allPhotoCounter];
		String[] photoCommentFirst = new String[allPhotoCounter];
		int pa = 1 + allPhotoCounter / 7;
		String pages = "" + pa;
		int count = 0;
		for (User userAll : allUser) {
			Set<Category> allCategory = userAll.getCategories();
			for (Category allCat : allCategory) {
				Set<Photo> allPhoto = allCat.getPhotos();
				for (Photo allPh : allPhoto) {
					pathAllFirst[count] = edit.getAbsolutePath(allPh);
					photoIdFirst[count] = "" + allPh.getPhotoId();
					photoNameFirst[count] = allPh.getPhName();
					photoCommentFirst[count] = "" + allPh.getComments().size();
//					System.out.println(allPh.getPhName());
					count++;
				}
			}
		}
//		System.out.println(request.getParameter("page"));
//		System.out.println(pa);
		int pageCurrent = 1;
		if (request.getParameter("page") != null){
			pageCurrent = Integer.parseInt(request.getParameter("page"));
		
		if (pageCurrent>pa || pageCurrent<1){
			pageCurrent=1;
		}
		now=0;
		System.out.println("teku6ta stranica :"+pageCurrent+" ob6to snimki "+ allPhotoCounter);
		int begin = pageCurrent*6 - 6;
//		System.out.println(begin);
		for (int k = begin; k < begin+6;k++){
			if (k < allPhotoCounter) {
				pathAll[now] = pathAllFirst[k];
				photoId[now] = photoIdFirst[k];
				photoName[now] = photoNameFirst[k];
				photoComment[now] = photoCommentFirst[k];
				now++;
			} 
	//		System.out.println(now);
		}
		}else{
			now = 0;
			for (int k = 0; k < 6;k++){
				if (k < allPhotoCounter) {
					pathAll[now] = pathAllFirst[k];
					photoId[now] = photoIdFirst[k];
					photoName[now] = photoNameFirst[k];
					photoComment[now] = photoCommentFirst[k];
					now++;
				} 
			}
			
	
			
		}
		
		System.out.println("minawam");
		session.setAttribute("nowPage", pageCurrent);
		session.setAttribute("ref", "aaa");
		session.setAttribute("allUser", allUser);
		session.setAttribute("allPhotoCounter", allPhotoCounter);
	//	session.setAttribute("pag", pages);
		session.setAttribute("pathAll", pathAll);
		session.setAttribute("photoId", photoId);
		session.setAttribute("photoName", photoName);
		session.setAttribute("photoComment", photoComment);
		request.getRequestDispatcher("MainPage.jsp").forward(request, response);
	}
}