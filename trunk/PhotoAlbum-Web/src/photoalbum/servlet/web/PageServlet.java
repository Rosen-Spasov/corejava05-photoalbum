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
		int allPhotoCounter = 0;
		String pageAction = request.getParameter("page");
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
					pathAllFirst[count] = WebBean.firstPath + allPh.getPath()
							+ allPh.getPhName();
					photoIdFirst[count] = "" + allPh.getPhotoId();
					photoNameFirst[count] = allPh.getPhName();
					photoCommentFirst[count] = "" + allPh.getComments().size();
					count++;
				}
			}
		}
		int nextPage;
		if (session.getAttribute("nextPage")!=null){
		nextPage = Integer.valueOf((Integer)session.getAttribute("nextPage"));
		}else{
			nextPage = 0;
		}
		System.out.println("sled ->"+nextPage);
		int nPage;
		
			nPage= nextPage;
		
		String[] pathAll = new String[6];
		String[] photoId = new String[6];
		String[] photoName = new String[6];
		String[] photoComment = new String[6];
		
		
		
		if (pageAction != null){
			int now = 0;
			if (nPage > allPhotoCounter){
				nPage = 0;
			}
			int next = nPage + 6;
			int prev = nPage - 6;
			if (prev < 0){
				prev = 0;
			}else if(prev > allPhotoCounter){
				prev=0;
			}
			System.out.println("-->sega" +nPage +" prev->"+prev+ " next->" + next);
		if (pageAction.equalsIgnoreCase("next")) {
			System.out.println("next");
			now=0;
			for (int begin = nPage; begin < next; begin++) {
				
				if (begin < allPhotoCounter) {
					pathAll[now] = pathAllFirst[begin];
					photoId[now] = photoIdFirst[begin];
					photoName[now] = photoNameFirst[begin];
					photoComment[now] = photoCommentFirst[begin];
					now++;
				} 
			}
			nPage = next;
		}
		if (pageAction.equalsIgnoreCase("prev")) {
			System.out.println("prev");
			now = 0;
			for (int begin = prev; begin < prev +6; begin++) {
				if (begin < allPhotoCounter) {
					pathAll[now] = pathAllFirst[begin];
					photoId[now] = photoIdFirst[begin];
					photoName[now] = photoNameFirst[begin];
					photoComment[now] = photoCommentFirst[begin];
					now++;
				}}
			nPage = prev+6;
			}
		}else{
			System.out.println("null");
			nPage=6;
			for (int k=0;k<6;k++){
				pathAll[k] = pathAllFirst[k];
				photoId[k] = photoIdFirst[k];
				photoName[k] = photoNameFirst[k];
				photoComment[k] = photoCommentFirst[k];
				System.out.println("---"+k);
			}
		}
		
		System.out.println("sega sme na ->"+nPage);
		session.setAttribute("nextPage", nPage);
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