package photoalbum.web.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadFile;
import photoalbum.entities.Category;

/**
 * Servlet implementation class for Servlet: UploadServlet
 *
 */
public class UploadServlet extends BaseServlet {

	private static final long serialVersionUID = -3738719329311650397L;
	
	public static final int FILE_SIZE_LIMIT = 5 * 1024 * 1024;
	
	public static final String WHITE_LIST = "*.png,*.gif,*.jpg";
	
	public static final String ATTR_SELECTED_CATEGORY = UserServlet.ATTR_SELECTED_CATEGORY;

	public UploadServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		if (MultipartFormDataRequest.isMultipartFormData(request)) {
			//	Parse multipart HTTP POST request.
			MultipartFormDataRequest mrequest = null;
			UploadBean uploadBean = new UploadBean();
			try {
				Category category = (Category) session.getAttribute(ATTR_SELECTED_CATEGORY);
				
				mrequest = new MultipartFormDataRequest(request);
				if (mrequest != null) {
					uploadBean.setStoremodel(UploadBean.MEMORYSTORE);
					uploadBean.setFilesizelimit(FILE_SIZE_LIMIT);
					uploadBean.setWhitelist(WHITE_LIST);
					uploadBean.store(mrequest);
					
					String todo = mrequest.getParameter("todo");
					if ( todo != null && todo.equalsIgnoreCase("upload") ) {
						Vector uploadFiles = uploadBean.getMemorystore();
						for (Object obj : uploadFiles) {
							UploadFile uploadFile = (UploadFile) obj;
							String fileName = uploadFile.getFileName();
							byte[] image = uploadFile.getData();
							getPam().addPhoto(category, fileName, image);
						}
					} else {
						getLogger().log("ERROR: todo=" + todo);
					}
				}
			} catch (Exception e) {
				getLogger().log(e);
			} finally {
				try {
					uploadBean.resetStore();
				} catch (UploadException e) {
					getLogger().log(e);
				}
				response.sendRedirect("./user?action=REFRESH");
			}
		}
	}
}