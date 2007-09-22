package photoalbum.servlet.web;

import java.io.IOException;
import java.util.ArrayList;
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

public class SearchPhotoServlet extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {

	public SearchPhotoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String searchName = (String) request.getParameter("searchName");
		searchName = searchName.toLowerCase();
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		User[] allUser = edit.getAllUsers();
		String[] errors = new String[4];
		if (validate(searchName, errors)) {
			ArrayList<String> path = new ArrayList<String>();
			ArrayList<String> photoId = new ArrayList<String>();
			ArrayList<String> photoName =new ArrayList<String>();
			ArrayList<String> photoComment = new ArrayList<String>();
			ArrayList<String> owner = new ArrayList<String>();
			for (User userAll : allUser) {
				Set<Category> allCategory = userAll.getCategories();
				for (Category allCat : allCategory) {
					Set<Photo> allPhoto = allCat.getPhotos();
					for (Photo allPh : allPhoto) {
						if (allPh.getPhName().toLowerCase().contains(searchName)){
							System.out.println(allPh.getPhName());
						path.add(edit.getAbsolutePath(allPh));
						photoId.add("" + allPh.getPhotoId());
						photoName.add(allPh.getPhName());
						photoComment.add("" + allPh.getComments().size());
						owner.add(userAll.getUsername());
						}
					}
				}
			}
			String[] pathArr = new String[path.size()];
			String[] photoIdArr = new String[photoId.size()];
			String[] photoNameArr = new String[photoId.size()];
			String[] photoCommentArr =new String[photoId.size()];
			String[] ownerArr = new String[photoId.size()];
			path.toArray(pathArr);
			photoId.toArray(photoIdArr);
			photoName.toArray(photoNameArr);
			photoComment.toArray(photoCommentArr);
			owner.toArray(ownerArr);
			
			session.setAttribute("pathAllCurrent",pathArr);
			session.setAttribute("photoIdCurrent",photoIdArr);
			session.setAttribute("photoNameCurrent",photoNameArr);
			session.setAttribute("photoCommentCurrent",photoCommentArr);
			session.setAttribute("owner", ownerArr);
			
			System.out.println(searchName);
			System.out.println("namereni rezult: "+ pathArr.length);
			request.getRequestDispatcher("ShowPageServlet").forward(request,
					response);
		}else{
			session.setAttribute("errors", errors);
			request.getRequestDispatcher("Search.jsp").forward(request,
					response);
		}
	}

	private boolean validate(String newName, String[] errors) {

		System.out.println(newName);
		
		boolean result = true;
		if (newName.length()<1) {
			errors[1] = "Enter name";
			result = false;
		} 
		String target = "(([0-9]+)?([A-Za-z]+)?)(([0-9]+)?([A-Za-z]+)?)*";
		if (newName.matches(target))	{
			errors[2]="Enter valid name";
			result = false;
		}	
	    
		System.out.println(result);
		return result;
	}
}