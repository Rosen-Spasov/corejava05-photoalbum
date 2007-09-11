package photoalbum.servlet.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.WebBean;

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
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public PageServlet() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("ahahahah");
		
		int allPhotoCounter = 0;
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		User[] allUser = edit.getAllUsers();
		
		for (User userAll : allUser) {
			Set<Category> allCategory = userAll.getCategories();
			for (Category allCat : allCategory) {
				Set<Photo> allPhoto = allCat.getPhotos();
				for (Photo allPh : allPhoto) {
					allPhotoCounter++;
				}
			}
		}
		
		
		
		String path;
		String[] pathAll = new String[allPhotoCounter];
		String[] photoId = new String[allPhotoCounter];
		String[] photoName = new String[allPhotoCounter];
		String[] photoComment = new String[allPhotoCounter];
		String pages =""+ 1 + allPhotoCounter / 6;
		System.out.println(pages);
		int count = 0;
		for (User userAll : allUser) {
			Set<Category> allCategory = userAll.getCategories();
			for (Category allCat : allCategory) {
				Set<Photo> allPhoto = allCat.getPhotos();
				for (Photo allPh : allPhoto) {
					pathAll[count]=WebBean.firstPath + allPh.getPath()+ allPh.getPhName();
					photoId[count]=""+allPh.getPhotoId();
					photoName[count]=allPh.getPhName();
					photoComment[count]=""+allPh.getComments().size();
					count++;
				}
			}
		}
		session.setAttribute("ref", "aaa");
		session.setAttribute("allUser", allUser);
		session.setAttribute("allPhotoCounter", allPhotoCounter);
		session.setAttribute("pag", pages);
		session.setAttribute("pathAll", pathAll);
		session.setAttribute("photoId", photoId);
		session.setAttribute("photoName", photoName);
		session.setAttribute("photoComment", photoComment);
		request.getRequestDispatcher("MainPage.jsp").forward(request, response);
	}
}