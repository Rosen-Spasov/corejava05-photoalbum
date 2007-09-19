package photoalbum.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;

 public class TestServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 7797544500379655596L;

	public TestServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	} 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PhotoAlbumManipulator pam = new PhotoAlbumManipulator();
		StringBuilder builder = new StringBuilder();
		
		User[] users = pam.getAllUsers();
		for (User user : users) {
			Set<Category> categories = user.getCategories();
			for (Category category : categories) {
				Set<Photo> photos = category.getPhotos();
				for (Photo photo : photos) {
					builder.append(pam.getAbsolutePath(photo) + "<br/>");
				}
			}
		}
		
		response.setContentType("text/html");
		PrintWriter writer = new PrintWriter(response.getOutputStream());
		writer.println("<html>");
		writer.println("<head>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println(builder.toString());
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
		
	}   	  	    
}