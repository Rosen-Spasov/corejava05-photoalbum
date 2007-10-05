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
	
	public static final String PARAM_ACTION = "action";
	
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
	
	public static enum Action {LOAD, REFRESH, NEXT_PAGE, PREV_PAGE}

	public UserServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		try {
			String action = request.getParameter(PARAM_ACTION);
			switch (Action.valueOf(action)) {
			case REFRESH:
				refreshUserInfo();
				break;
			case LOAD:
				loadUserInfo(request, response);
				break;
			case NEXT_PAGE:
				loadNextPage();
				break;
			case PREV_PAGE:
				loadPrevPage();
				break;
			default:
			}
		} finally {
			forwardRequest(request, response);
		}
	}
	
	private void refreshUserInfo() {

		User selectedUser = (User) session.getAttribute(ATTR_SELECTED_USER);
		if (selectedUser != null) {
			int userId = selectedUser.getUserId();
			selectedUser = getPam().getUserById(userId);
			session.setAttribute(ATTR_SELECTED_USER, selectedUser);
		}
		
		int totalPages = 0;
		Category selectedCategory = (Category) session.getAttribute(ATTR_SELECTED_CATEGORY);
		if (selectedCategory != null) {
			int categoryId = selectedCategory.getCategoryId();
			selectedCategory = getPam().getCategoryById(categoryId);
			session.setAttribute(ATTR_SELECTED_CATEGORY, selectedCategory);
			
			PhotoPage[] photoPages = getPages(selectedCategory);
			if (photoPages != null) {
				session.setAttribute(ATTR_PHOTO_PAGES, photoPages);
				totalPages = photoPages.length;
			}
		}
		session.setAttribute(ATTR_PAGE_INDEX, 0);
		session.setAttribute(ATTR_TOTAL_PAGES, totalPages);
		
	}
	
	private void loadUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageIndex = 0;
		int totalPages = 0;

		try {
			int userId = Integer.parseInt(request.getParameter(PARAM_USER_ID));
			User selectedUser = getPam().getUserById(userId);
			session.setAttribute(ATTR_SELECTED_USER, selectedUser);
		} catch (NumberFormatException e) {
			session.setAttribute(ATTR_SELECTED_USER, null);
			getLogger().log("Could not parse user ID or no user ID has been provided at all.");
		}

		try {
			int categoryId = Integer.parseInt(request.getParameter(PARAM_CATEGORY_ID));
			Category selectedCategory = getPam().getCategoryById(categoryId);
			session.setAttribute(ATTR_SELECTED_CATEGORY, selectedCategory);
			PhotoPage[] photoPages = getPages(selectedCategory);
			session.setAttribute(ATTR_PHOTO_PAGES, photoPages);
			if (photoPages != null) {
				totalPages = photoPages.length;
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
		
		session.setAttribute(ATTR_PAGE_INDEX, pageIndex);
		session.setAttribute(ATTR_TOTAL_PAGES, totalPages);
	}
	
	private PhotoPage[] getPages(Category category) {
		if (category == null) {
			return null;
		}
		
		Set<Photo> photos = category.getPhotos();
		PhotoPage[] photoPages = PhotoPage.getPages(photos);
		
		return photoPages;
	}
	
	private void loadNextPage() {
		if (session.getAttribute(ATTR_PAGE_INDEX) != null) {
			int pageIndex = (Integer) session.getAttribute(ATTR_PAGE_INDEX);
			if (session.getAttribute(ATTR_TOTAL_PAGES) != null) {
				int totalPages = (Integer) session.getAttribute(ATTR_TOTAL_PAGES);
				if (pageIndex < totalPages - 1) {
					pageIndex++;
					session.setAttribute(ATTR_PAGE_INDEX, pageIndex);
				}
			}
		}
	}
	
	private void loadPrevPage() {
		if (session.getAttribute(ATTR_PAGE_INDEX) != null) {
			int pageIndex = (Integer) session.getAttribute(ATTR_PAGE_INDEX);
			if (pageIndex > 0) {
				pageIndex--;
				session.setAttribute(ATTR_PAGE_INDEX, pageIndex);
			}
		}
	}
	
	private void forwardRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(REQUEST_DISPATCHER).forward(request, response);
	}
}