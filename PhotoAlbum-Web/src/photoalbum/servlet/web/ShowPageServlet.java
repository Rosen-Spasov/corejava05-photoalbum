package photoalbum.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;

import bean.WebBean;

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
		String pageAction = (String)request.getParameter("page");
		System.out.println(pageAction);
		String[] pathCurrent = (String[])session.getAttribute("pathAll");
		String[] photoIdCurrent = (String[])session.getAttribute("photoId");
		String[] photoNameCurrent = (String[])session.getAttribute("photoName");
		String[] photoCommentCurrent = (String[])session.getAttribute("photoComment");
		System.out.println("snimki->"+pathCurrent.length);
		int allPhotoCounter = pathCurrent.length;
		int pa = 1 + allPhotoCounter / 7;
		String pages = "" + pa;
		Integer nextPage = Integer.valueOf((Integer)session.getAttribute("nextPage"));
		
		System.out.println("sled ->"+nextPage);
		int nPage;
		if (nextPage == null) {
			nPage = 0;
		}else{
			nPage= nextPage;
		}
		String[] path = new String[6];
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
					path[now] = WebBean.firstPath + pathCurrent[begin] + photoNameCurrent[begin];
					photoId[now] = photoIdCurrent[begin];
					photoName[now] = photoNameCurrent[begin];
					photoComment[now] = photoCommentCurrent[begin];
					System.out.println(path[now]);
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
					path[now] = WebBean.firstPath + pathCurrent[begin] + photoName[begin];
					photoId[now] = photoIdCurrent[begin];
					photoName[now] = photoNameCurrent[begin];
					photoComment[now] = photoCommentCurrent[begin];
					now++;
				}}
			nPage = prev+6;
			}
		}else{
			System.out.println("null");
			System.out.println(allPhotoCounter);
			nPage=6;
			for (int k=0;k<6;k++){
				if (k<allPhotoCounter){
				path[k] = WebBean.firstPath + pathCurrent[k] + photoNameCurrent[k];
				photoId[k] = photoIdCurrent[k];
				photoName[k] = photoNameCurrent[k];
				photoComment[k] = photoCommentCurrent[k];
			
				}
			}
		}
		System.out.println(path.length);
		System.out.println(allPhotoCounter);
		System.out.println("sega sme na ->"+nPage);
		session.setAttribute("nextPage", nPage);
		session.setAttribute("pag", pages);
		session.setAttribute("allPhotoCounter", allPhotoCounter);
		session.setAttribute("pathAll", path);
		session.setAttribute("photoId", photoId);
		session.setAttribute("photoName", photoName);
		session.setAttribute("photoComment", photoComment);
	
		
		request.getRequestDispatcher("ShowUser.jsp").forward(request, response);
	}   	  	    
}