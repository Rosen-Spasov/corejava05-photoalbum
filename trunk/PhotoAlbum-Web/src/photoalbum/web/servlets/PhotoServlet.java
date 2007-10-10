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
	
	public static final String PARAM_PHOTO_ID = "photoId";
	
	public static final String PARAM_PHOTO_NAME = "phName";
	
	public static final String ATTR_SELECTED_PHOTO = "selectedPhoto";
	
	public static final String DEFAULT_REDIRECT_PAGE = "./photo.jsp";
	
	public static final String USER_REDIRECT_PAGE = "./user?action=REFRESH";
	
	public static enum Action {LOAD, REFRESH, DELETE, RENAME}

	public PhotoServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String redirectPage = DEFAULT_REDIRECT_PAGE;
		
		try {
			String action = request.getParameter(PARAM_ACTION);
			if (action != null) {
				switch (Action.valueOf(action)) {
				case REFRESH:
					refresh();
					break;
				case LOAD:
					int photoId = Integer.parseInt(request.getParameter(PARAM_PHOTO_ID));
					loadPhoto(photoId);
					break;
				case DELETE:
					photoId = Integer.parseInt(request.getParameter(PARAM_PHOTO_ID));
					deletePhoto(photoId);
					redirectPage = USER_REDIRECT_PAGE;
					break;
				case RENAME:
					photoId = Integer.parseInt(request.getParameter(PARAM_PHOTO_ID));
					String phName = request.getParameter(PARAM_PHOTO_NAME);
					renamePhoto(photoId, phName);
					break;
				default:
				}
			}
		} catch (NumberFormatException e) {
			getLogger().log(e);			
		} finally {
			request.getRequestDispatcher(redirectPage).forward(request, response);
		}
	}
	
	private void loadPhoto(int photoId) {
		Photo selectedPhoto = getPam().getPhotoById(photoId);
		if (selectedPhoto != null) {
			session.setAttribute(ATTR_SELECTED_PHOTO, selectedPhoto);
		}
	}
	
	private void deletePhoto(int photoId) {
		Photo photo = getPam().getPhotoById(photoId);
		if (photo != null) {
			getPam().deletePhoto(photo);
		}
	}
	
	private void renamePhoto(int photoId, String phName) {
		Photo photo = getPam().getPhotoById(photoId);
		getPam().renamePhoto(photo, phName);
	}
	
	private void refresh() {
		if (session.getAttribute(ATTR_SELECTED_PHOTO) != null) {
			Photo photo = (Photo) session.getAttribute(ATTR_SELECTED_PHOTO);
			photo = getPam().getPhotoById(photo.getPhotoId());
			if (photo != null) {
				getPam().refresh(photo);
				session.setAttribute(ATTR_SELECTED_PHOTO, photo);
			}
		}
	}
}