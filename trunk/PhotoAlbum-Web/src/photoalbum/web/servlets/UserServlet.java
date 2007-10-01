package photoalbum.web.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.web.util.PhotoPage;

/**
 * Servlet implementation class for Servlet: UserServlet
 *
 */
public class UserServlet extends photoalbum.web.servlets.BaseServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 2867274932955083992L;
	
	public static final String PARAM_USER_ID = "userId";
	
	public static final String PARAM_CATEGORY_ID = "categoryId";
	
	public static final String PARAM_PAGE_INDEX = "pageIndex";
	
	public static final String ATTR_PAGE_INDEX = PARAM_PAGE_INDEX;
	
	public static final String ATTR_SELECTED_USER = "selectedUser";
	
	public static final String ATTR_SELECTED_CATEGORY = "selectedCategory";
	
	public static final String ATTR_PHOTO_PAGES = "photoPages";
	
	public static final String ATTR_TOTAL_PAGES = "totalPages";
	
	public static final String REQUEST_DISPATCHER = "user.jsp";
	
	public static final int PHOTOS_PER_PAGE = 16;

	public UserServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		int pageIndex = 0;
		int totalPages = 0;
		try {
			try {
				int userId = Integer.parseInt(request.getParameter(PARAM_USER_ID));
				User selectedUser = getPam().getUserById(userId);
				if (selectedUser != null) {
					session.setAttribute(ATTR_SELECTED_USER, selectedUser);
				} else {
					session.setAttribute(ATTR_SELECTED_USER, null);
				}
			} catch (NumberFormatException e) {
				session.setAttribute(ATTR_SELECTED_USER, null);
				getLogger().log("Could not parse user ID or no user ID has been provided at all.");
			}
			
			try {
				int categoryId = Integer.parseInt(request.getParameter(PARAM_CATEGORY_ID));
				Category selectedCategory = getPam().getCategoryById(categoryId);
				if (selectedCategory != null) {
					session.setAttribute(ATTR_SELECTED_CATEGORY, selectedCategory);
					Set<Photo> photos = selectedCategory.getPhotos();
					PhotoPage[] photoPages = PhotoPage.getPages(photos);
					if (photoPages != null) {
						session.setAttribute(ATTR_PHOTO_PAGES, photoPages);
						totalPages = photoPages.length;
					}
				} else {
					session.setAttribute(ATTR_SELECTED_CATEGORY, null);
					session.setAttribute(ATTR_PHOTO_PAGES, null);
				}
			} catch (NumberFormatException e) {
				session.setAttribute(ATTR_SELECTED_CATEGORY, null);
				session.setAttribute(ATTR_PHOTO_PAGES, null);
				getLogger().log("Could not parse category ID or no category ID has been provided at all.");
			}
			
			try {
				pageIndex = Integer.parseInt(request.getParameter(PARAM_PAGE_INDEX));
			} catch (NumberFormatException e) {
				getLogger().log("Could not parse page index or no page index has been provided at all.");
			}
		} finally {
			request.getRequestDispatcher(REQUEST_DISPATCHER + "?pageIndex=" + pageIndex + "&totalPages=" + totalPages).forward(request, response);
		}
	}   	  	    
}