package photoalbum.web.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import photoalbum.PhotoAlbumManipulator;
import photoalbum.logging.Logger;

/**
 * Servlet implementation class for Servlet: BaseServlet
 * This is the base servlet which is extended by all servlets in the system.
 */
public abstract class BaseServlet extends HttpServlet implements Servlet {
	
	public static final String ATTR_INITIALIZED = "initialized";
	
	public static final String ATTR_LOGGER = "logger";
	
	public static final String ATTR_PAM = "pam";
	
	protected HttpSession session;
	
	protected boolean initialized = false;
	
	protected PhotoAlbumManipulator pam;
	
	protected Logger logger;
	
	public BaseServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		if (session.getAttribute(ATTR_INITIALIZED) != null) {
			initialized = (Boolean) session.getAttribute(ATTR_INITIALIZED);
		}
		if (initialized) {
			pam = (PhotoAlbumManipulator) session.getAttribute(ATTR_PAM);
			logger = (Logger) session.getAttribute(ATTR_LOGGER);
		}
	}
}
