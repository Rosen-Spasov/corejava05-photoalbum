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
   
   public static final String PARAM_QUERY_STRING = "queryString";
	
   public static final String PARAM_PAGE_INDEX = "pageIndex";

   public static final String ATTR_FOUND_PAGE_INDEX = PARAM_PAGE_INDEX;

   public static final String ATTR_FOUND_PHOTOS = "foundPhotos";

   public static final String ATTR_FOUND_PHOTO_PAGES = "foundPhotoPages";

   public static final String ATTR_TOTAL_FOUND_PAGES_PAGES = "totalPages";

   public static final String REQUEST_DISPATCHER = "searchResult.jsp";

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
		   if (action != null){
			   switch (Action.valueOf(action)) {
			   case LOADPHOTOS:
				   loadPhotos(request, response);
				   break;
			   case NEXT_PAGE:
				   loadNextPage();
				   break;
			   case PREV_PAGE:
				   loadPrevPage();
				   break;
			   default:
			   }
		   }
	   } finally {
		   forwardRequest(request, response);
	   }


   }

   private void loadPhotos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   int foundPageIndex = 0;
	   int totalFoundPages = 0;

	   String queryString = request.getParameter(PARAM_QUERY_STRING);
	   List<Photo> foundPhotos = getPam().searchPhotos(queryString);
	   if (foundPhotos != null) {
		   PhotoPage[] foundPhotoPages = PhotoPage.getPages(foundPhotos);
		   if (foundPhotoPages != null) {
			   session.setAttribute(ATTR_FOUND_PHOTO_PAGES, foundPhotoPages);
			   totalFoundPages = foundPhotoPages.length;
		   }
	   }

	   try {
		   foundPageIndex = Integer.parseInt(request.getParameter(PARAM_PAGE_INDEX));
	   } catch (NumberFormatException e) {
		   getLogger().log("Could not parse page index or no page index has been provided at all.");
	   }

	   session.setAttribute(ATTR_FOUND_PAGE_INDEX, foundPageIndex);
	   session.setAttribute(ATTR_TOTAL_FOUND_PAGES_PAGES, totalFoundPages);
   }



   private void loadNextPage() {
	   if (session.getAttribute(ATTR_FOUND_PAGE_INDEX) != null) {
		   int pageIndex = (Integer) session.getAttribute(ATTR_FOUND_PAGE_INDEX);
		   if (session.getAttribute(ATTR_TOTAL_FOUND_PAGES_PAGES) != null) {
			   int totalPages = (Integer) session.getAttribute(ATTR_TOTAL_FOUND_PAGES_PAGES);
			   if (pageIndex < totalPages - 1) {
				   pageIndex++;
				   session.setAttribute(ATTR_FOUND_PAGE_INDEX, pageIndex);
			   }
		   }
	   }
   }

   private void loadPrevPage() {
	   if (session.getAttribute(ATTR_FOUND_PAGE_INDEX) != null) {
		   int pageIndex = (Integer) session.getAttribute(ATTR_FOUND_PAGE_INDEX);
		   if (pageIndex > 0) {
			   pageIndex--;
			   session.setAttribute(ATTR_FOUND_PAGE_INDEX, pageIndex);
		   }
	   }
   }

   private void forwardRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.getRequestDispatcher(REQUEST_DISPATCHER).forward(request, response);
   }
	
}   	  	    
