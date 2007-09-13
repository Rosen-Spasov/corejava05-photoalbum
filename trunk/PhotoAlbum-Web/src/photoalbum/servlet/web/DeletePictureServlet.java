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
 * Servlet implementation class for Servlet: DeletePictureServlet
 * 
 */
public class DeletePictureServlet extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DeletePictureServlet() {
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
		String pic = request.getParameter("pic");
		System.out.println(pic);
		User user = (User) session.getAttribute("user");
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		if (user != null) {
			int photoId = Integer.parseInt((String) request.getParameter("pic"));
			String path = null;
			Set<Category> category = user.getCategories();
			for (Category categ : category) {
				Set<Photo> photos = categ.getPhotos();
				for (Photo ph : photos) {
					if (ph.getPhotoId() == photoId) {
						edit.delete(ph);
					}
				}
			}
		}
		request.getRequestDispatcher("ShowUser.jsp").forward(request, response);
	}
}