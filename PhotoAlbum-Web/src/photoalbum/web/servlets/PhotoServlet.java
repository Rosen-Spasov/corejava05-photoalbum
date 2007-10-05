package photoalbum.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.entities.Photo;

/**
 * Servlet implementation class for Servlet: PhotoServlet
 *
 */
 public class PhotoServlet extends photoalbum.web.servlets.BaseServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = -6726354809040799124L;
	
	public static final String PARAM_ACTION = "action";
	
	public static final String PARAM_PAGE_INDEX = UserServlet.PARAM_PAGE_INDEX;
	
	public static final String PARAM_PHOTO_ID = "photoId";
	
	public static final String ATTR_SELECTED_PHOTO = "selectedPhoto";
	
	public static final String ATTR_PHOTO_PAGES = UserServlet.ATTR_PHOTO_PAGES;
	
	public static enum Action {LOAD, REFRESH, ADD, DELETE, RENAME}

	public PhotoServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		try {
			String action = request.getParameter(PARAM_ACTION);
			if (action != null) {
				switch (Action.valueOf(action)) {
				case LOAD:
					int photoId = Integer.parseInt(request.getParameter(PARAM_PHOTO_ID));
					loadPhoto(photoId);
					break;
				case REFRESH:
					break;
				case ADD:
					break;
				default:
					break;
				}
			}
		} finally {
			response.sendRedirect("./photo.jsp");
		}
	}
	
	private void loadPhoto(int photoId) {
		Photo selectedPhoto = getPam().getPhotoById(photoId);
		if (selectedPhoto != null) {
			session.setAttribute(ATTR_SELECTED_PHOTO, selectedPhoto);
		}
	}
	
	private void addPhoto() {
//		getPam().addp
	}
}