package photoalbum.web.servlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.CreateUserException;
import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.User;
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
		Enumeration names = this.getServletContext().getAttributeNames();
		try {
			Object obj = oiStream.readObject();
			if (obj instanceof Object[]) {
				Object[] inputData = (Object[]) obj;
				Object outputData = null;
				if (inputData.length > 0) {
					String cmd = (String) inputData[0];
					if (NetworkConnection.CMD_ADMIN_ACCESS_GRANTED.equals(cmd) && inputData.length == 2) {
						outputData = adminAccessGranted(inputData[1]);
					} else if (NetworkConnection.CMD_GET_ALL_USERS.equals(cmd)) {
						outputData = getAllUsers();
					} else if (NetworkConnection.CMD_ADD_USER.equals(cmd) && inputData.length == 2) {
						outputData = addUser(inputData[1]);
					} else if (NetworkConnection.CMD_UPDATE_USER.equals(cmd) && inputData.length == 2) {
						updateUser(inputData[1]);
					} else if (NetworkConnection.CMD_DELETE_OBJECT.equals(cmd) && inputData.length == 2) {
						delete(inputData[1]);
					}
					writeData(response, outputData);
				}
			}
		} catch (ClassNotFoundException e) {
			Logger.getDefaultInstance().log(e);
		} catch (Throwable e) {
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
	
	private void updateUser(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			getPhotoAlbumManipulator().updateUser(user);
		}
	}
	
	private void delete(Object obj) {
		getPhotoAlbumManipulator().delete(obj);
	}
}