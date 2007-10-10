package photoalbum.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.entities.Photo;
import photoalbum.web.util.PhotoPage;

/**
 * Servlet implementation class for Servlet: SearchServlet
 *
 */
 public class SearchServlet extends BaseServlet {
   static final long serialVersionUID = 1L;
   
   public static final String PARAM_ACTION = "action";
	
	public static final String PARAM_PAGE_INDEX = "pageIndex";
	
	public static final String ATTR_PAGE_INDEX = PARAM_PAGE_INDEX;
	
	public static final String ATTR_SEARCH_PHOTOS = "searchPhotos";
	
	public static final String ATTR_PHOTO_PAGES = "photoPages";
	
	public static final String ATTR_TOTAL_PAGES = "totalPages";
	
	public static final String REQUEST_DISPATCHER = "searchResult.jsp";
	
	public static final int PHOTOS_PER_PAGE = 16;
	
	public static enum Action {LOADPHOTOS, NEXT_PAGE, PREV_PAGE}
  
	public SearchServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	
		
		
		try {
			String action = request.getParameter(PARAM_ACTION);
			if (action==null){
				action = "LOADPHOTOS";
			}
			switch (Action.valueOf(action)) {
			case LOADPHOTOS:
				getPhotosByName(request, response);
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
		
	private void getPhotosByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageIndex = 0;
		int totalPages = 0;

		String searchName = request.getParameter("searchName");
		System.out.println(searchName);
		List<Photo> searchPhotos = getPam().searchPhotos(searchName);
		
		try {
			
			
			session.setAttribute(ATTR_SEARCH_PHOTOS, searchPhotos);
			
			
		} catch (NumberFormatException e) {
			
			getLogger().log("Could not parse user ID or no user ID has been provided at all.");
		}

		try {
		
			PhotoPage[] photoPages = PhotoPage.getPages(searchPhotos);
			session.setAttribute(ATTR_PHOTO_PAGES, photoPages);
			if (photoPages != null) {
				totalPages = photoPages.length;
			}
		} catch (NumberFormatException e) {
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
