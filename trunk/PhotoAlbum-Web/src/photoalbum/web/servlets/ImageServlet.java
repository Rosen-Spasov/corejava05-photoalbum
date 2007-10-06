package photoalbum.web.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.entities.Photo;
import photoalbum.filesystem.FileSystemManager;

/**
 * Servlet implementation class for Servlet: ImageServlet
 *
 */
 public class ImageServlet extends photoalbum.web.servlets.BaseServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = -2111167673444442935L;
	
	public static final int BUFF_SIZE = FileSystemManager.BUFF_SIZE ;
	
	public static final String CONTENT_TYPE = "image/*";
	
	public static final String PARAM_PHOTO_ID = "photoId";

	public ImageServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		try {
			int photoId = Integer.parseInt(request.getParameter(PARAM_PHOTO_ID));
			Photo photo = getPam().getPhotoById(photoId);
			if (photo != null) {
				String absolutePath = getPam().getAbsolutePath(photo);
				File image = new File(absolutePath);
				if (image.exists() && image.isFile()) {
					writeImage(response, image);
				}
			}
		} catch (NumberFormatException e) {
			getLogger().log(e);
		}
	}
	
	private void writeImage(HttpServletResponse response, File image) throws IOException {
		
		response.setContentType(CONTENT_TYPE);
		
		FileInputStream fiStream = new FileInputStream(image);
		OutputStream responseOutput = response.getOutputStream();
		
		byte[] buffer = new byte[BUFF_SIZE];
		int bytesRead = -1;
		while ((bytesRead = fiStream.read(buffer)) != -1) {
			responseOutput.write(buffer, 0, bytesRead);
		}
		responseOutput.flush();
	}
}