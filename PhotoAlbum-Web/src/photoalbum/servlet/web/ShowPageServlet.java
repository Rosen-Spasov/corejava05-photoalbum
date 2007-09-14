package photoalbum.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;


/**
 * Servlet implementation class for Servlet: ShowPageServlet
 *
 */
 public class ShowPageServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	public ShowPageServlet() {
		super();
	}   	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int photoAtPage = Integer.valueOf((Integer)session.getAttribute("photoAtPage"));
		String pageAction = (String)request.getParameter("page");
//		System.out.println(pageAction);
		String[] pathCurrent = (String[])session.getAttribute("pathAllCurrent");
		String[] photoIdCurrent = (String[])session.getAttribute("photoIdCurrent");
		String[] photoNameCurrent = (String[])session.getAttribute("photoNameCurrent");
		String[] photoCommentCurrent = (String[])session.getAttribute("photoCommentCurrent");
		String[] owner = null;
		if (session.getAttribute("owner")!=null){
		owner = (String[])session.getAttribute("owner");
		}
//		System.out.println("snimki->"+pathCurrent.length);
		int allPhotoCounter = pathCurrent.length;
		String pages = (String)session.getAttribute("pages");
		
		int nPage = 0;
		int nextPage = 0;
		if (session.getAttribute("nextPage") != null) {
			nextPage = Integer.valueOf((Integer)session.getAttribute("nextPage"));
			nPage = nextPage;
		}
//		System.out.println("sled ->"+nextPage);
		String[] path = new String[photoAtPage];
		String[] photoId = new String[photoAtPage];
		String[] photoName = new String[photoAtPage];
		String[] photoComment = new String[photoAtPage];
		
		if (pageAction != null){
			int now = 0;
			if (nPage > allPhotoCounter){
				nPage = 0;
			}
			int next = nPage + photoAtPage;
			int prev = nPage - photoAtPage;
			if (prev < 0){
				prev = 0;
			}else if(prev > allPhotoCounter){
				prev=0;
			}
	//		System.out.println("-->sega" +nPage +" prev->"+prev+ " next->" + next);
		if (pageAction.equalsIgnoreCase("next")) {
			System.out.println("next");
			now=0;
			for (int begin = nPage; begin < next; begin++) {
				
				if (begin < allPhotoCounter) {
					path[now] = pathCurrent[begin];
					photoId[now] = photoIdCurrent[begin];
					photoName[now] = photoNameCurrent[begin];
					photoComment[now] = photoCommentCurrent[begin];
	//				System.out.println(path[now]);
					now++;
				} 
			}
			nPage = next;
		}
		if (pageAction.equalsIgnoreCase("prev")) {
//			System.out.println("prev");
			now = 0;
			for (int begin = prev; begin < prev +photoAtPage; begin++) {
				if (begin < allPhotoCounter) {
					path[now] = pathCurrent[begin];
					photoId[now] = photoIdCurrent[begin];
					photoName[now] = photoNameCurrent[begin];
					photoComment[now] = photoCommentCurrent[begin];
					now++;
				}}
			nPage = prev+photoAtPage;
			}
		}else{
	//		System.out.println("null");
	//		System.out.println(allPhotoCounter);
			nPage=photoAtPage;
			for (int k=0;k<photoAtPage;k++){
				if (k<allPhotoCounter){
				path[k] = pathCurrent[k] ;
				photoId[k] = photoIdCurrent[k];
				photoName[k] = photoNameCurrent[k];
				photoComment[k] = photoCommentCurrent[k];
				if (owner!=null){
				//	owner[k] = owner[k];
				}
				}
			}
		}
//		System.out.println(path.length);
//		System.out.println(allPhotoCounter);
//		System.out.println("sega sme na ->"+nPage);
		
		session.setAttribute("nextPage", nPage);
		
		session.setAttribute("pathAll", path);
		session.setAttribute("photoId", photoId);
		session.setAttribute("photoName", photoName);
		session.setAttribute("photoComment", photoComment);
	
		
		request.getRequestDispatcher("ShowUser.jsp").forward(request, response);
	}   	  	    
}