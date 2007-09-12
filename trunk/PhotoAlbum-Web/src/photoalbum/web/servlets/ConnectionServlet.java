package photoalbum.web.servlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.CreateCategoryException;
import photoalbum.CreatePhotoException;
import photoalbum.CreateUserException;
import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.entities.interfaces.ICategoryContainer;
import photoalbum.logging.Logger;
import photoalbum.network.NetworkConnection;

 public class ConnectionServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = -6318374395824273211L;
	
	private static PhotoAlbumManipulator photoAlbumManipulator = null;

	public ConnectionServlet() {
		super();
	}
	
	public void init() throws ServletException {
		super.init();
	}
	
	private static PhotoAlbumManipulator getPhotoAlbumManipulator() {
		if (photoAlbumManipulator == null) {
			photoAlbumManipulator = new PhotoAlbumManipulator();
		}
		return photoAlbumManipulator;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectInputStream oiStream = new ObjectInputStream(request.getInputStream());
		try {
			Object obj = oiStream.readObject();
			if (obj instanceof Object[]) {
				Object[] inputData = (Object[]) obj;
				Object outputData = null;
				if (inputData.length > 0) {
					String cmd = inputData[0].toString();
					if (NetworkConnection.CMD_ADMIN_ACCESS_GRANTED.equals(cmd) && inputData.length == 2) {
						outputData = adminAccessGranted(inputData[1]);
					} else if (NetworkConnection.CMD_GET_ALL_USERS.equals(cmd)) {
						outputData = getAllUsers();
					} else if (NetworkConnection.CMD_ADD_USER.equals(cmd) && inputData.length == 2) {
						outputData = addUser(inputData[1]);
					} else if (NetworkConnection.CMD_UPDATE_USER.equals(cmd) && inputData.length == 2) {
						updateUser(inputData[1]);
					} else if (NetworkConnection.CMD_DELETE.equals(cmd) && inputData.length == 2) {
						delete(inputData[1]);
					} else if (NetworkConnection.CMD_ADD_CATEGORY.equals(cmd) && inputData.length == 3) {
						outputData = addCategory(inputData[1], inputData[2]);
					} else if (NetworkConnection.CMD_ADD_PHOTO.equals(cmd) && inputData.length == 4) {
						outputData = addPhoto(inputData[1], inputData[2], inputData[3]);
					} else if (NetworkConnection.CMD_REFRESH.equals(cmd) && inputData.length ==2) {
						outputData = refresh(inputData[1]);
					}
				}
				writeData(response, outputData);
			}
		} catch (ClassNotFoundException e) {
			Logger.getDefaultInstance().log(e);
		}
	}
	
	private void writeData(HttpServletResponse response, Object outputData) throws IOException {
		ObjectOutputStream ooStream = new ObjectOutputStream(response.getOutputStream());
		ooStream.writeObject(outputData);
		ooStream.flush();
	}
	
	private Boolean adminAccessGranted(Object obj) {
		Boolean result = false;
		if (obj instanceof String) {
			String password = (String) obj;
			result = PhotoAlbumManipulator.adminAccessGranted(password);
		}
		return result;
	}
	
	private User[] getAllUsers() {
		return getPhotoAlbumManipulator().getAllUsers();
	}
	
	private CreateUserException addUser(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			try {
				getPhotoAlbumManipulator().addUser(user);
			} catch (CreateUserException e) {
				Logger.getDefaultInstance().log(e);
				return e;
			}
		}
		return null;
	}
	
	private Category addCategory(Object parent, Object catName) {
		Category category = null;

		if (parent instanceof ICategoryContainer && catName instanceof String) {
			try {
				category = getPhotoAlbumManipulator().addCategory((ICategoryContainer) parent, (String) catName);
			} catch (CreateCategoryException e) {
				Logger.getDefaultInstance().log(e);
			}
		}
		
		return category;
	}
	
	private Photo addPhoto(Object parent, Object phName, Object image) {
		Photo photo = null;
		
		if (parent instanceof Category && phName instanceof String && image instanceof byte[]) {
			try {
				photo = getPhotoAlbumManipulator().addPhoto((Category) parent, (String) phName, (byte[]) image);
			} catch (CreatePhotoException e) {
				Logger.getDefaultInstance().log(e);
			}
		}
		
		return photo;
	}
	
	private void updateUser(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			getPhotoAlbumManipulator().updateUser(user);
		}
	}
	
	private void delete(Object obj) {
		getPhotoAlbumManipulator().delete(obj);
	}
	
	private Object refresh(Object obj) {
		getPhotoAlbumManipulator().refresh(obj);
		return obj;
	}
}